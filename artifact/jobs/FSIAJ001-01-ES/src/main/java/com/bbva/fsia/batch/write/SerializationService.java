package com.bbva.fsia.batch.write;

import com.bbva.fsia.batch.soap.CombinedDateConverter;

import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.dto.artica.xml.Header;
import com.bbva.fsia.dto.artica.xml.VirtualCurrency;
import com.bbva.dtod.dto.artica.xml.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.util.Collection;

public class SerializationService<T> {
    private XStream xstream = new XStream();

    public SerializationService() {
        // Register the custom date converter
        xstream.registerConverter(new CombinedDateConverter());
        //xstream.registerConverter(new DateConverter());

        // Security permissions (allow java.sql.Date)
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypesByWildcard(new String[]{
                "com.bbva.fsia.dto.artica.xml.*",
                "com.bbva.fsia.dto.artica.xmlresp.*",
                "com.bbva.dtod.dto.artica.xml.*",
                "com.bbva.fsia.dto.trade.*",
                "java.util.Date",
                "java.sql.Date"
        });

        // Omit the 'class' attribute for Date fields
        xstream.omitField(java.util.Date.class, "class");

        // Aliases and annotations for XML serialization
        xstream.alias("dec:Declaracion", DeclarationModel172.class);
        xstream.processAnnotations(DeclarationModel172.class);
        xstream.processAnnotations(Header.class);
        xstream.aliasField("dec1:FechaFinCustodia", VirtualCurrency.class, "custodyEndDate");
        xstream.registerLocalConverter(VirtualCurrency.class, "custodyEndDate", new DateConverter("YYYY-MM-DD", new String[] {"YYYY-MM-DD"}));
        // Process annotations for DTO classes
        xstream.processAnnotations(CabeceraDI.class);
        xstream.processAnnotations(RespuestaDeclaracionType.class);
        xstream.processAnnotations(DatosPresentacionType.class);

        xstream.processAnnotations(IDDeclarante.class);
        xstream.processAnnotations(RespuestaOperacionesType.class);
        xstream.processAnnotations(ErrorType.class);

    }

    @SuppressWarnings("unchecked")
    public T deserialize(String xml) {
        return (T) xstream.fromXML(xml);
    }

    public String serialize(T obj) {
        return xstream.toXML(obj);
    }
}