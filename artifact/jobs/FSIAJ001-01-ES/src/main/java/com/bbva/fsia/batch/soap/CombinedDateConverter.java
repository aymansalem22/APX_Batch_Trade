package com.bbva.fsia.batch.soap;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CombinedDateConverter implements Converter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public boolean canConvert(Class type) {
        return type.equals(LocalDate.class) || type.equals(Date.class);
    }

    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
        if (source instanceof LocalDate) {
            LocalDate date = (LocalDate) source;
            writer.setValue(DATE_FORMATTER.format(date)); // Format as "YYYY-MM-DD"
        } else if (source instanceof Date) {
            Date date = (Date) source;
            writer.setValue(TIMESTAMP_FORMATTER.format(date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())); // Format as "dd-MM-yyyy HH:mm:ss"
        }
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        String value = reader.getValue();
        if (value == null || value.isEmpty()) {
            return null; // Handle empty or missing timestamps gracefully
        }
        if (value.length() == 10) {
            return LocalDate.parse(value, DATE_FORMATTER); // Parse date
        } else {
            return Date.from(LocalDateTime.parse(value, TIMESTAMP_FORMATTER).atZone(java.time.ZoneId.systemDefault()).toInstant()); // Parse timestamp
        }
    }
}