package com.bbva.fsia.dto.artica.xml;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public Date unmarshal(String v) throws Exception {
        return v != null ? dateFormat.parse(v) : null;
    }

    @Override
    public String marshal(Date v) throws Exception {
        return v != null ? dateFormat.format(v) : null;
    }
}
