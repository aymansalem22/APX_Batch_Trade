package com.bbva.fsia.batch.write;


import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JavaUtilDateConverter implements Converter {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Date date = (Date) source;
        writer.setValue(dateFormat.format(date)); // Format as "YYYY-MM-DD"
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        try {
            return dateFormat.parse(reader.getValue());
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse date", e);
        }
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class); // Handle java.util.Date
    }
}