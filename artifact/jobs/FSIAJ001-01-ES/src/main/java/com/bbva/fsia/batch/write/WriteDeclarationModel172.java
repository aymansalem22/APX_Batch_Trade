package com.bbva.fsia.batch.write;


import com.bbva.fsia.dto.artica.xml.DeclarationModel172;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.List;

public class WriteDeclarationModel172 implements ItemWriter<DeclarationModel172> {

    private final StaxEventItemWriter<DeclarationModel172> writer;

    @Autowired
    public WriteDeclarationModel172(Jaxb2Marshaller marshaller) {
        this.writer = new StaxEventItemWriter<>();
        this.writer.setMarshaller(marshaller);
        this.writer.setRootTagName("declarations");
    }

    public void setResource(Resource resource) {
        this.writer.setResource(resource);
    }

    @Override
    public void write(List<? extends DeclarationModel172> items) throws Exception {
        writer.write(items);
    }
}