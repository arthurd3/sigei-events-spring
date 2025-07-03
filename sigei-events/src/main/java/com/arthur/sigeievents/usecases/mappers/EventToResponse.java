package com.arthur.sigeievents.usecases.mappers;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.domain.entities.Event;
import com.arthur.sigeievents.domain.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EventToResponse {

    public Optional<EventResponseDTO> convertToResponse(Event event) {
        User ownerUser = event.getOwnerUser();
        String ownerName = (ownerUser != null) ? ownerUser.getUsername() : null;
        Long ownerId = (ownerUser != null) ? ownerUser.getId() : null;
        String ownerEmail = (ownerUser != null) ? ownerUser.getEmail() : null;

        Long availableVacancies = event.getEventSize() - event.getEventParticipants().stream().count();

        var convertEvent = new EventResponseDTO(
                event.getId(),
                event.getEventName(),
                event.getEventDescription(),
                event.getEventType(),
                event.getEventDate(),
                event.getEventSize(),
                ownerName,
                ownerId,
                ownerEmail,
                event.getEventImage(),
                event.getEventLocation(),
                availableVacancies
        );

        return Optional.of(convertEvent);
    }

    public List<EventResponseDTO> convertListToResponse(List<Event> events) {

        if(events == null)
            return new ArrayList<>();


        List<EventResponseDTO> returnList = events
                .stream()
                .map(event -> convertToResponse(event).get())
                .collect(Collectors.toList());

        return returnList;
    }
}
