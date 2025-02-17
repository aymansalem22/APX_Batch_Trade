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
import java.util.List;

public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);

    private WritableResource resource; // ✅ Use WritableResource

    public void setResource(Resource resource) {
        this.resource = (WritableResource) resource; // ✅ Ensure it's writable
    }


    @Override
    public void write(List<? extends DeclarationModel172> list) throws Exception {
        if (list == null || list.isEmpty()) {
            System.out.println("No data to write");
            return;
        }

        System.out.println("⏳ Start writing XML...");

        if (resource == null) {
            System.out.println("❌ Resource is NULL! Cannot write XML.");
            return;
        }

        File outputFile = resource.getFile();
        File parentDir = outputFile.getParentFile();
        if (!parentDir.exists()) {
            boolean created = parentDir.mkdirs();
            System.out.println("✅ Created output directory: " + parentDir.getAbsolutePath());
        }

        System.out.println("📂 Writing XML to: " + outputFile.getAbsolutePath());

        JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationModel172.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE); // Avoid XML declaration

        try (StringWriter stringWriter = new StringWriter()) {
            stringWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
            for (DeclarationModel172 declaration : list) {
                marshaller.marshal(declaration, stringWriter);
                stringWriter.write("\n"); // Add a newline after each declaration
            }

            try (OutputStream os = resource.getOutputStream()) {
                os.write(stringWriter.toString().getBytes());
                System.out.println("✅ XML writing completed successfully!");
            }
        } catch (Exception e) {
            System.out.println("❌ Error while writing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}