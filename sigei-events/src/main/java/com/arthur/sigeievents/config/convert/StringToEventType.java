package com.arthur.sigeievents.config.convert;

import com.arthur.sigeievents.domain.enuns.EventType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEventType implements Converter<String, EventType> {

    @Override
    public EventType convert(String source) {

        if (source == null || source.isEmpty()) {
            return null;
        }

        return EventType.valueOf(source.toUpperCase());
    }
}
