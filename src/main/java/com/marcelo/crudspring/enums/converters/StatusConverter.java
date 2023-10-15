package com.marcelo.crudspring.enums.converters;

import com.marcelo.crudspring.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }

        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(Status.values())
                .filter(v -> v.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
