package com.bbva.fsia.batch.soap;


import com.bbva.fsia.dto.artica.xmlresponsev2.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

import java.util.Collection;


public class SerializationService2<T> {



        private XStream xstream = new XStream();

        public SerializationService2() {
            // Enable security
            xstream.addPermission(NoTypePermission.NONE);
            xstream.addPermission(NullPermission.NULL);
            xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
            xstream.allowTypeHierarchy(Collection.class);

            // Allow specific packages/classes
            xstream.allowTypesByWildcard(new String[]{
                    "com.bbva.fsia.dto.artica.xmlresponsev2.*"
            });

            // Process annotations for DTO classes
            xstream.processAnnotations(RespuestaDeclaracionType.class);
            xstream.processAnnotations(DatosPresentacionType.class);
            xstream.processAnnotations(CabeceraDI.class);
            xstream.processAnnotations(IDDeclarante.class);
            xstream.processAnnotations(RespuestaOperacionesType.class);
            xstream.processAnnotations(ErrorType.class);

            // Register the custom Date converter
            xstream.registerConverter(new DateConverter());
        }

        public T deserialize(String xml) {
            return (T) xstream.fromXML(xml);
        }

        public String serialize(T obj) {
            return xstream.toXML(obj);
        }
    }

