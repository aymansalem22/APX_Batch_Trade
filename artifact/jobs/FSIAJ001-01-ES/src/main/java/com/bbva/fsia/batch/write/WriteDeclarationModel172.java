package com.bbva.fsia.batch.write;


import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.dto.artica.xml.DeclaredEntity;
import com.bbva.fsia.dto.artica.xml.VirtualCurrency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);

    private WritableResource resource; // ✅ Use WritableResource
    private DeclarationModel172 declarationModel = new DeclarationModel172(); // ✅ Store all declared entities
    private static final SimpleDateFormat XML_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy"); // ✅ Correct Date Format

    public void setResource(Resource resource) {
        this.resource = (WritableResource) resource; // ✅ Ensure it's writable
    }


    @Override
    public void write(List<? extends DeclarationModel172> list) throws Exception {
        if (list == null || list.isEmpty()) {
            System.out.println("No data to write");
            return;
        }

        System.out.println("⏳ Collecting declared entities...");
        if (declarationModel.getCabecera() == null && !list.isEmpty()) {
            declarationModel.setCabecera(list.get(0).getCabecera());
        }
        if (declarationModel.getDeclaredEntities() == null) {
            declarationModel.setDeclaredEntities(new ArrayList<>());
        }
        for (DeclarationModel172 item : list) {
            declarationModel.getDeclaredEntities().addAll(item.getDeclaredEntities());
        }

        File outputFile = resource.getFile();
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            System.out.println("✅ Created output directory: " + parentDir.getAbsolutePath());
        }

        System.out.println("📂 Writing XML to: " + outputFile.getAbsolutePath());

        try (OutputStream os = resource.getOutputStream(); StringWriter stringWriter = new StringWriter()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationModel172.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            // ✅ Write the SOAP Envelope
            stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            stringWriter.write("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n");
            stringWriter.write("                  xmlns:dec=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/Declaracion.xsd\"\n");
            stringWriter.write("                  xmlns:dec1=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/DeclaracionInformativa.xsd\">\n");

            stringWriter.write("<soapenv:Header/>\n");
            stringWriter.write("<soapenv:Body>\n");

            marshaller.marshal(declarationModel, stringWriter);

            stringWriter.write("</soapenv:Body>\n");
            stringWriter.write("</soapenv:Envelope>\n");

            os.write(stringWriter.toString().getBytes());
            System.out.println("✅ XML writing completed successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error while writing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }

}