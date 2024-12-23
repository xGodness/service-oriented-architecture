package ru.xgodness.util;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateJAXBAdapter extends XmlAdapter<String, LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate unmarshal(String value) {
        return (value != null && !value.isEmpty()) ? LocalDate.parse(value, FORMATTER) : null;
    }

    @Override
    public String marshal(LocalDate date) {
        return (date != null) ? date.format(FORMATTER) : null;
    }
}
