package com.bbva.fsia.batch.write;

import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import com.bbva.fsia.dto.artica.xml.Header;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.util.Collection;

public class SerializationService <T>{
    private XStream xstream = new XStream();

    public SerializationService() {
        xstream.addPermission(NoTypePermission.NONE);
        xstream.addPermission(NullPermission.NULL);
        xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xstream.allowTypeHierarchy(Collection.class);
        xstream.allowTypesByWildcard(new String[] {
                "com.bbva.fsia.dto.artica.xml.*",
                "com.bbva.fsia.dto.trade.*"
        });
        xstream.alias("dec:Declaracion", DeclarationModel172.class);
        xstream.processAnnotations(DeclarationModel172.class);
        xstream.processAnnotations(Header.class);

    }

    public T deserialize(String xml) {
        return (T) xstream.fromXML(xml);
    }

    public String serialize(T object) {
        return xstream.toXML(object);
    }

}
