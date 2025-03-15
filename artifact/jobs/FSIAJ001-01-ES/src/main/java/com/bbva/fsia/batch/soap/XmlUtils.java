package com.bbva.fsia.batch.soap;

import lombok.experimental.var;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


public class XmlUtils {



        public static String extractSoapBody(String soapResponse) throws Exception {
            // Parse the SOAP response to extract the <RespuestaDeclaracion> element
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // Enable namespace awareness
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(soapResponse)));

            // Find the <RespuestaDeclaracion> element
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//*[local-name()='RespuestaDeclaracion']";
            Node respuestaDeclaracionNode = (Node) xPath.evaluate(expression, document, XPathConstants.NODE);

            if (respuestaDeclaracionNode == null) {
                throw new IllegalArgumentException("Could not find <RespuestaDeclaracion> in SOAP response.");
            }

            // Convert the node back to a string
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(respuestaDeclaracionNode), new StreamResult(writer));
            return writer.toString();
        }


    }