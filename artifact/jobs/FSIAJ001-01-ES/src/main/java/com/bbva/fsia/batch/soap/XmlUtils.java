package com.bbva.fsia.batch.soap;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmlUtils {



    public static String extractSoapBody(String soapResponse) {
        String startTag = "<ddiiR:RespuestaDeclaracion";
        String endTag = "</ddiiR:RespuestaDeclaracion>";

        int startIndex = soapResponse.indexOf(startTag);
        int endIndex = soapResponse.indexOf(endTag);

        if (startIndex == -1 || endIndex == -1) {
            return "";
        }

        int startTagEndIndex = soapResponse.indexOf(">", startIndex);
        if (startTagEndIndex == -1) {
            return "";
        }

        // Extract full XML content
        String extractedXml = soapResponse.substring(startIndex, endIndex + endTag.length());

        // Fix the opening tag issue: Ensure <RespuestaDeclaracion> is in one line
        extractedXml = cleanOpeningTag(extractedXml);

        // Remove namespaces
        extractedXml = removeNamespaces(extractedXml);

        // Clean unnecessary spaces or newlines between tags
        extractedXml = extractedXml.replaceAll(">\\s+<", "><");

        return extractedXml.trim();
    }

    private static String cleanOpeningTag(String xml) {
        Pattern pattern = Pattern.compile("(<ddiiR:RespuestaDeclaracion)(\\s+[^>]*)?>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(xml);
        if (matcher.find()) {
            xml = matcher.replaceFirst("<ddiiR:RespuestaDeclaracion>");
        }
        return xml;
    }

    public static String removeNamespaces(String xml) {
        xml = xml.replaceAll("xmlns(:\\w+)?=\"[^\"]*\"", "");  // Remove namespace declarations
        xml = xml.replaceAll("(<|</)\\w+:", "$1");  // Remove namespace prefixes
        return xml.trim();
    }
}
