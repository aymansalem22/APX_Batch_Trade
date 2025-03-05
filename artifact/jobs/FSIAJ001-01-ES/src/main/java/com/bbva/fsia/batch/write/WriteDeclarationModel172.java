package com.bbva.fsia.batch.write;


import com.bbva.fsia.dto.artica.xml.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;


import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {
    private static final Logger LOGGER = LoggerFactory.getLogger(WriteDeclarationModel172.class);



    private Resource resource;
    private XStream xstream;

    public WriteDeclarationModel172() {
        xstream = new XStream(new StaxDriver());
        xstream.autodetectAnnotations(true); // Enable XStream annotations

        // Configure XStream to use the correct aliases
        xstream.alias("Declaracion", DeclarationModel172.class);
        xstream.alias("Cabecera", Header.class);
        xstream.alias("IDDeclarante", Declarant.class);
        xstream.alias("PersonaContacto", ContactPerson.class);
        xstream.alias("Declarado", DeclaredEntity.class);
        xstream.alias("Domicilio", Address.class);
        xstream.alias("MonedaVirtual", VirtualCurrency.class);

        // Configure XStream to handle namespaces
        xstream.alias("soapenv:Envelope", Envelope.class);
        xstream.alias("soapenv:Body", Body.class);
        xstream.alias("dec:Declaracion", DeclarationModel172.class);
        xstream.alias("dec1:Cabecera", Header.class);
        xstream.alias("dec1:Declarado", DeclaredEntity.class);

        // Register custom converters
        xstream.registerConverter(new CustomDateConverter());
        xstream.registerConverter(new CustomCollectionConverter(xstream.getMapper()));
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void write(List<? extends DeclarationModel172> items) throws Exception {
        Assert.notNull(resource, "Resource must not be null");

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(resource.getFile()), StandardCharsets.UTF_8)) {
            // Write the XML declaration
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            // Write the root element with namespaces
            writer.write("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n" +
                    "                  xmlns:dec=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/Declaracion.xsd\"\n" +
                    "                  xmlns:dec1=\"https://www2.agenciatributaria.gob.es/static_files/common/internet/dep/aplicaciones/es/aeat/ddii/enol/ws/DeclaracionInformativa.xsd\">\n");

            // Write the <soapenv:Header/> tag
            writer.write("    <soapenv:Header/>\n");

            // Write the body
            writer.write("    <soapenv:Body>\n");

            // Write the <dec:Declaracion> tag
            writer.write("        <dec:Declaracion>\n");

            // Write each item
            for (DeclarationModel172 item : items) {
                String xml = xstream.toXML(item);
                // Remove the XML declaration from the XStream output
                xml = xml.replace("<?xml version='1.0' encoding='UTF-8'?>", "");
                writer.write(xml);
            }

            // Close the <dec:Declaracion>, <soapenv:Body>, and <soapenv:Envelope> tags
            writer.write("        </dec:Declaracion>\n");
            writer.write("    </soapenv:Body>\n");
            writer.write("</soapenv:Envelope>\n");
        }
    }

    // Custom Date Converter to remove "class" attributes
    private static class CustomDateConverter extends AbstractSingleValueConverter {
        private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        @Override
        public boolean canConvert(Class type) {
            LOGGER.info("Checking if CustomDateConverter can convert: {}", type.getName());
            return Date.class.isAssignableFrom(type);
        }

        @Override
        public String toString(Object obj) {
            LOGGER.info("Converting date: {}", obj);
            return dateFormat.format((Date) obj);
        }

        @Override
        public Object fromString(String s) {
            return null;
        }
    }

    // Custom Collection Converter to remove "class" attributes
    private static class CustomCollectionConverter extends CollectionConverter {
        public CustomCollectionConverter(Mapper mapper) {
            super(mapper);
        }

        @Override
        public boolean canConvert(Class type) {
            LOGGER.info("Checking if CustomCollectionConverter can convert: {}", type.getName());
            return Collection.class.isAssignableFrom(type);
        }
    }
}