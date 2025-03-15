package com.bbva.fsia.batch.soap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class JAXBUtils {

    /**
     * Converts an XML string into a Java object.
     *
     * @param xml   The XML string to unmarshal.
     * @param clazz The class type to convert the XML into.
     * @param <T>   The type of the Java object.
     * @return The unmarshalled Java object.
     * @throws JAXBException If an error occurs during unmarshalling.
     */
    public static <T> T unmarshal(String xml, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (T) unmarshaller.unmarshal(new StringReader(xml));
    }

    /**
     * Converts a Java object into an XML string.
     *
     * @param object The Java object to marshal.
     * @return The XML representation of the object.
     * @throws JAXBException If an error occurs during marshalling.
     */
    public static String marshal(Object object) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(object.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter writer = new StringWriter();
        marshaller.marshal(object, writer);
        return writer.toString();
    }
}