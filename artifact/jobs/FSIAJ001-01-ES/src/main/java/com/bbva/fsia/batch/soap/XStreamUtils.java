package com.bbva.fsia.batch.soap;

import com.bbva.fsia.dto.artica.xmlresponsev2.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.util.List;

public class XStreamUtils {

    public static <T> T unmarshal(String xml, Class<T> clazz) {
        XStream xStream = new XStream(new StaxDriver());
        configureXStream(xStream);
        return clazz.cast(xStream.fromXML(xml));
    }

    private static void configureXStream(XStream xStream) {
        // Allow only safe classes
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{
                RespuestaDeclaracionType.class,
                DatosPresentacionType.class,
                CabeceraDI.class,
                IDDeclarante.class,
                RespuestaOperacionesType.class,
                ErrorType.class
        });

        // Register aliases for all DTO classes
        registerAliases(xStream);

        // Handle implicit collections
        xStream.addImplicitCollection(RespuestaDeclaracionType.class, "respuestaLinea", "RespuestaLinea", RespuestaOperacionesType.class);

        // Ignore unknown elements
        xStream.ignoreUnknownElements();
    }

    private static void registerAliases(XStream xStream) {
        // Root element
        xStream.alias("RespuestaDeclaracion", RespuestaDeclaracionType.class);

        // Fields in RespuestaDeclaracionType
        xStream.aliasField("CSV", RespuestaDeclaracionType.class, "csv");
        xStream.aliasField("DatosPresentacion", RespuestaDeclaracionType.class, "datosPresentacion");
        xStream.aliasField("Cabecera", RespuestaDeclaracionType.class, "cabecera");
        xStream.aliasField("EstadoEnvio", RespuestaDeclaracionType.class, "estadoEnvio");

        // Fields in DatosPresentacionType
        xStream.alias("DatosPresentacion", DatosPresentacionType.class);
        xStream.aliasField("NIFPresentador", DatosPresentacionType.class, "nifPresentador");
        xStream.aliasField("TimestampPresentacion", DatosPresentacionType.class, "timestampPresentacion");

        // Fields in CabeceraDI
        xStream.alias("Cabecera", CabeceraDI.class);
        xStream.aliasField("TipoComunicacion", CabeceraDI.class, "tipoComunicacion");
        xStream.aliasField("Modelo", CabeceraDI.class, "modelo");
        xStream.aliasField("Ejercicio", CabeceraDI.class, "ejercicio");
        xStream.aliasField("IDVersionModelo", CabeceraDI.class, "idVersionModelo");
        xStream.aliasField("IDDeclarante", CabeceraDI.class, "idDeclarante");

        // Fields in IDDeclarante
        xStream.alias("IDDeclarante", IDDeclarante.class);
        xStream.aliasField("NIF", IDDeclarante.class, "nif");
        xStream.aliasField("NombreRazon", IDDeclarante.class, "nombreRazon");

        // Fields in RespuestaOperacionesType
        xStream.alias("RespuestaLinea", RespuestaOperacionesType.class);
        xStream.aliasField("IDRegistroDeclarado", RespuestaOperacionesType.class, "idRegistroDeclarado");
        xStream.aliasField("EstadoRegistro", RespuestaOperacionesType.class, "estadoRegistro");
        xStream.aliasField("CodigoErrorRegistro", RespuestaOperacionesType.class, "codigoErrorRegistro");
        xStream.aliasField("DescripcionErrorRegistro", RespuestaOperacionesType.class, "descripcionErrorRegistro");
        xStream.aliasField("CSV", RespuestaOperacionesType.class, "csv");
        xStream.aliasField("Errores", RespuestaOperacionesType.class, "errores");

        // Fields in ErrorType

        // Map the <Error> elements inside <Errores>
        xStream.alias("Error", ErrorType.class);

        // Fields in ErrorType
        xStream.aliasField("CodigoError", ErrorType.class, "codigoError");
        xStream.aliasField("DescripcionError", ErrorType.class, "descripcionError");
        xStream.aliasField("ElementoError", ErrorType.class, "elementoError");


    }




    public static String removeNamespaces(String xml) {
        // Remove all namespace declarations
        xml = xml.replaceAll("xmlns(:\\w+)?=\"[^\"]*\"", "");

        // Remove namespace prefixes (e.g., env:, ddiiR:, ddii:) but NOT from timestamp values
        xml = xml.replaceAll("(<|</)\\w+:", "$1");  // Keeps opening/closing tags intact

        return xml.trim(); // Trim whitespace
    }

}