package com.bbva.fsia.batch.soap;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter {

    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    @Override
    public boolean canConvert(Class type) {
        return type.equals(Date.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        Date date = (Date) source;
        writer.setValue(FORMATTER.format(date));
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String value = reader.getValue();
        if (value == null || value.isEmpty()) {
            return null; // Handle empty or missing timestamps gracefully
        }
        try {
            // Validate the timestamp format
            return FORMATTER.parse(value); // Attempt to parse the timestamp
        } catch (ParseException e) {
            // Log a warning and handle the malformed timestamp gracefully
            System.err.println("Malformed timestamp encountered: " + value);
            return null; // Return null for invalid timestamps
        }
    }
}