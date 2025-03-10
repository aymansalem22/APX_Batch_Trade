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


    public WriteDeclarationModel172() {
        xStream = new XStream(new StaxDriver());
    }



    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void write(List<? extends DeclaredEntity> items) throws Exception {
        if (resource == null) {
            throw new IllegalStateException("Output resource must be set.");
        }

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n")
                .append("                  xmlns:dec=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/Declaracion.xsd\"\n")
                .append("                  xmlns:dec1=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/DeclaracionInformativa.xsd\">\n")
                .append("<soapenv:Header/>\n")
                .append("<soapenv:Body>\n")
                .append("<dec:Declaracion>\n");

        // Serialize Header

        SerializationService<Header> headerSerializationService = new SerializationService<>();
        String serializedHeader = headerSerializationService.serialize(header);
        xmlBuilder.append(serializedHeader).append("\n");

        SerializationService<DeclaredEntity> serializationService = new SerializationService<>();


        for (DeclaredEntity item : items) {
            String serializedItem = serializationService.serialize(item);
            xmlBuilder.append(serializedItem).append("\n");
        }

        xmlBuilder.append("</dec:Declaracion>\n")
                .append("</soapenv:Body>\n")
                .append("</soapenv:Envelope>");

        String finalBody = xmlBuilder.toString();
        LOGGER.info("Final body: {}", finalBody);

      //  finalBody = finalBody.replaceAll(" class=\"sql-date\"", "");

        try (FileOutputStream fos = new FileOutputStream(resource.getFile());
             Writer writer = new OutputStreamWriter(fos, "UTF-8")) {
            writer.write(finalBody);
            writer.flush();
        } catch (IOException e) {
            LOGGER.error("Error writing to file", e);
            throw e;
        }
    }

    private String serializeHeader(Header header) {
        SerializationService<Header> headerSerializationService = new SerializationService<>();
        return headerSerializationService.serialize(header);
    }

//    private Header createHeader() {
//        Header header = new Header();
//        header.setCommunicationType("A0");
//        header.setModel("172");
//        header.setFiscalYear("2023");
//        header.setModelVersionId("1.0");
//
//        Declarant declarant = new Declarant();
//        declarant.setTaxId("A48265169");
//        declarant.setCompanyName("BANCO BILBAO VIZCAYA ARGENTARIA S.A.");
//        declarant.setRepresentativeTaxId("XXXXXXXX");
//
//        header.setDeclarant(declarant);
//        return header;
//    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // Retrieve the header from the step context
        this.header = (Header) stepExecution.getExecutionContext().get("header");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }
}