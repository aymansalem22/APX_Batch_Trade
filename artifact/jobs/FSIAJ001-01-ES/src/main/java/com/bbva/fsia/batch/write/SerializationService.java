package com.bbva.fsia.batch.write;

import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.dto.artica.xml.Header;

import com.bbva.fsia.dto.artica.xml.VirtualCurrency;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.util.Collection;

public class SerializationService<T> {
        private XStream xstream = new XStream();

        public SerializationService() {
            // Register the custom date converter
            xstream.registerConverter(new JavaUtilDateConverter());

            // Security permissions (allow java.sql.Date)
            xstream.addPermission(NoTypePermission.NONE);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.allowTypeHierarchy(Collection.class);
            xstream.allowTypesByWildcard(new String[]{
                    "com.bbva.fsia.dto.artica.xml.*",
                    "com.bbva.fsia.dto.trade.*",
                    "java.util.Date",
                    "java.sql.Date" // Add this line
            });

            // 3. Omit the 'class' attribute for Date fields
            xstream.omitField(java.util.Date.class, "class");


            // Aliases and annotations
            xstream.alias("dec:Declaracion", DeclarationModel172.class);
            xstream.processAnnotations(DeclarationModel172.class);
            xstream.processAnnotations(Header.class);

            // Explicit field alias for FechaFinCustodia
            xstream.aliasField("dec1:FechaFinCustodia", VirtualCurrency.class, "custodyEndDate");
        }





    @SuppressWarnings("unchecked")
    public String serialize(T object) {
        return xstream.toXML(object);
    }

    //deserialize
    public T deserialize(String xml) {
        return (T) xstream.fromXML(xml);
    }


}
