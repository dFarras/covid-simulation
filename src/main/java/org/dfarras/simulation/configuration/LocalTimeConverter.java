package org.dfarras.simulation.configuration;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
@ConfigurationPropertiesBinding
public class LocalTimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String value) {
        if(value == null) {
            return null;
        }
        return LocalTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
