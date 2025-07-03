package com.arthur.sigeievents.controllers.request;

import com.arthur.sigeievents.domain.enuns.EventType;

import java.sql.Date;


public record EventRequestDTO(String eventName,
                              String eventDescription,
                              EventType eventType,
                              Date eventDate,
                              Long eventSize,
                              String eventImage,
                              String eventLocation) {
}
