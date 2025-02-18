package com.bbva.fsia.batch.write;


import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);

    private WritableResource resource; // ✅ Use WritableResource
    private DeclarationModel172 declarationModel = new DeclarationModel172(); // ✅ Store all declared entities

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

        // ✅ If header is missing, set it from the first item
        if (declarationModel.getCabecera() == null && !list.isEmpty()) {
            declarationModel.setCabecera(list.get(0).getCabecera()); // ✅ Copy header from first record
        }

        // ✅ Ensure we only have ONE DeclarationModel172 and all declaredEntities are added
        if (declarationModel.getDeclaredEntities() == null) {
            declarationModel.setDeclaredEntities(new ArrayList<>());
        }

        for (DeclarationModel172 item : list) {
            declarationModel.getDeclaredEntities().addAll(item.getDeclaredEntities());
        }

        // ✅ Write only once to the XML file
        File outputFile = resource.getFile();
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            System.out.println("✅ Created output directory: " + parentDir.getAbsolutePath());
        }

        System.out.println("📂 Writing XML to: " + outputFile.getAbsolutePath());

        try (OutputStream os = resource.getOutputStream()) {
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationModel172.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(declarationModel, os);
            System.out.println("✅ XML writing completed successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error while writing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}