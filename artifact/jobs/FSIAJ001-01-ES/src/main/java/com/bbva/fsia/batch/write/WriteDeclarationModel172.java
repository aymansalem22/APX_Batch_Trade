package com.bbva.fsia.batch.write;


import com.bbva.apx.exception.io.IOException;
import com.bbva.fsia.dto.artica.xml.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);


    private Resource resource;
    private XStream xStream;

    public WriteDeclarationModel172() {
        xStream = new XStream(new StaxDriver());
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }


    @Override
    public void write(List<? extends DeclarationModel172> items) throws Exception {
        if (resource == null) {
            throw new IllegalStateException("Output resource must be set.");
        }

        // Set up XStream aliases with dec1 namespace for various elements
        xStream.alias("dec:Declaracion", DeclarationModel172.class);
        xStream.alias("dec1:Cabecera", Header.class);
        xStream.alias("dec1:Declarado", DeclaredEntity.class);
        xStream.alias("dec1:MonedaVirtual", VirtualCurrency.class);
        xStream.alias("dec1:Domicilio", Address.class);
        xStream.alias("dec1:IDMonedas", VirtualCurrency.class);

        // Add collection alias for lists (e.g., virtualCurrencies in DeclaredEntity)
        xStream.addImplicitCollection(DeclaredEntity.class, "virtualCurrencies");

        // Add permissions for the classes to be serialized/deserialized
        xStream.allowTypes(new Class[]{DeclarationModel172.class, Header.class, DeclaredEntity.class, VirtualCurrency.class, Address.class});

        // Serialize the object to XML (assuming a single item here for simplicity)
        DeclarationModel172 declaration = items.get(0);
        String xml = xStream.toXML(declaration);

        // Remove the extra `dec:Declaracion` wrapping (we already wrap it in the SOAP envelope)
        xml = xml.replaceFirst("<dec:Declaracion>", "").replaceFirst("</dec:Declaracion>", "");

        // Wrap the XML with the SOAP envelope
        String wrappedXml = wrapWithSoapEnvelope(xml);

        // Write the XML string to the output resource
        try (FileOutputStream fos = new FileOutputStream(resource.getFile())) {
            Writer writer = new OutputStreamWriter(fos, "UTF-8");
            writer.write(wrappedXml);
            writer.flush();
        }
    }

    private String wrapWithSoapEnvelope(String xml) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                "                  xmlns:dec=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/Declaracion.xsd\"\n" +
                "                  xmlns:dec1=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/DeclaracionInformativa.xsd\">\n" +
                "<soapenv:Header/>\n" +
                "<soapenv:Body>\n" +
                "<dec:Declaracion>\n" +
                xml +
                "</dec:Declaracion>\n" +
                "</soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
