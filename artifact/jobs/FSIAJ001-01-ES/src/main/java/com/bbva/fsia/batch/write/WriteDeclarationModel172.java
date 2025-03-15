package com.bbva.fsia.batch.write;

import com.bbva.apx.exception.io.IOException;
import com.bbva.fsia.batch.process.ProcessTrade;
import com.bbva.fsia.dto.artica.xml.Declarant;
import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.dto.artica.xml.DeclaredEntity;
import com.bbva.fsia.dto.artica.xml.Header;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.List;

public class WriteDeclarationModel172 implements ItemWriter<DeclaredEntity>, StepExecutionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);

    private Resource resource;
    private XStream xStream;
    private Header header;
    private String finalBody;


    public WriteDeclarationModel172() {
        xStream = new XStream(new StaxDriver());
    }



    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void write(List<? extends DeclaredEntity> items) throws Exception {
        if (items == null || items.isEmpty()) {
            LOGGER.warn("No items to process.");
            return;
        }

        // Validate consistency of operation type
        validateOperationType(items);

        // Determine the operation type
        String operationType = extractOperationType();

        // Build the SOAP request dynamically
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n")
                .append("                  xmlns:dec=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/Declaracion.xsd\"\n")
                .append("                  xmlns:dec1=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/DeclaracionInformativa.xsd\">\n")
                .append("<soapenv:Header/>\n")
                .append("<soapenv:Body>\n");

        // Add the root element based on the operation type
        String rootElement = getRootElement(operationType);
        xmlBuilder.append("<dec:").append(rootElement).append(">\n");

        // Serialize Header
        SerializationService<Header> headerSerializationService = new SerializationService<>();
        String serializedHeader = headerSerializationService.serialize(header);
        xmlBuilder.append(serializedHeader).append("\n");

        // Serialize Items
        SerializationService<DeclaredEntity> serializationService = new SerializationService<>();
        for (DeclaredEntity item : items) {
            String serializedItem = serializationService.serialize(item);
            xmlBuilder.append(serializedItem).append("\n");
        }

        // Close tags
        xmlBuilder.append("</dec:").append(rootElement).append(">\n")
                .append("</soapenv:Body>\n")
                .append("</soapenv:Envelope>");

        // Store the final XML body
        finalBody = xmlBuilder.toString();
        LOGGER.info("Generated SOAP Request: {}", finalBody);

        // Write the XML to the output file
        try (FileOutputStream fos = new FileOutputStream(resource.getFile());
             Writer writer = new OutputStreamWriter(fos, "UTF-8")) {
            writer.write(finalBody);
            writer.flush();
        } catch (IOException e) {
            LOGGER.error("Error writing to file", e);
            throw e;
        }
    }



    @Override
    public void beforeStep(StepExecution stepExecution) {
        // Retrieve the header from the step context
        this.header = (Header) stepExecution.getExecutionContext().get("header");
    }





    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if (finalBody != null) {
            stepExecution.getJobExecution().getExecutionContext().put("finalBody", finalBody);
            LOGGER.info("Promoted Final Body to Job Execution Context: {}", finalBody);
        } else {
            LOGGER.error("Final Body is null. Cannot promote to Job Execution Context.");
        }
        return ExitStatus.COMPLETED;
    }


    private String extractOperationType() {
        if (header == null || header.getCommunicationType() == null) {
            throw new IllegalStateException("Header or communication type is missing.");
        }
        return header.getCommunicationType();
    }


    private String getRootElement(String operationType) {
        switch (operationType) {
            case "A0": // Alta
            case "A1": // Modificación
                return "Declaracion";
            case "Baja":
                return "Baja";
            default:
                throw new IllegalArgumentException("Invalid operation type: " + operationType);
        }
    }

    private void validateOperationType(List<? extends DeclaredEntity> items) {
        if (items == null || items.isEmpty()) return;

        String operationType = extractOperationType();
        boolean allSameType = true;

        for (DeclaredEntity item : items) {
            // Assuming each DeclaredEntity has a reference to its Header or similar logic
            if (!operationType.equals(header.getCommunicationType())) {
                allSameType = false;
                break;
            }
        }

        if (!allSameType) {
            throw new IllegalArgumentException("Mixed operation types detected in the batch.");
        }
    }

}