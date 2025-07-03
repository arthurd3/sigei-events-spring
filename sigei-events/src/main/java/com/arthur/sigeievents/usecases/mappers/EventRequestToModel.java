package com.arthur.sigeievents.usecases.mappers;

import com.arthur.sigeievents.controllers.request.EventRequestDTO;
import com.arthur.sigeievents.domain.entities.Event;
import com.arthur.sigeievents.domain.entities.User;

import java.util.ArrayList;

public class EventRequestToModel {


    public Event convertToModel(EventRequestDTO eventDTO , User ownerUser) {
        return new Event(
                null,
                eventDTO.eventName(),
                eventDTO.eventDescription(),
                eventDTO.eventType(),
                eventDTO.eventDate(),
                eventDTO.eventSize(),
                eventDTO.eventImage(),
                eventDTO.eventLocation(),
                ownerUser,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }
}
