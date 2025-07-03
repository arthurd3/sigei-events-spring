package com.arthur.sigeievents.controllers.response;

import com.arthur.sigeievents.domain.enuns.EventType;

import java.sql.Date;

public record EventResponseDTO(Long id,
                               String eventName,
                               String eventDescription,
                               EventType eventType,
                               Date eventDate,
                               Long eventSize,
                               String ownerName,
                               Long ownerId,
                               String ownerEmail,
                               String eventImage,
                               String eventLocation,
                               Long availableVacancies) {
}
