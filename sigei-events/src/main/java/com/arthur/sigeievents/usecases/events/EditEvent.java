package com.arthur.sigeievents.usecases.events;

import com.arthur.sigeievents.config.security.model.UserLogged;
import com.arthur.sigeievents.controllers.request.EventRequestDTO;
import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.domain.entities.Event;
import com.arthur.sigeievents.repositories.EventsRepository;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class EditEvent {
    private EventsRepository eventsRepository;
    private EventToResponse  eventToResponse;

    public Optional<EventResponseDTO> editEvent(Long eventId, EventRequestDTO eventEditDTO) {

        return eventsRepository.findById(eventId)
                .map(eventToUpdate -> {
                    BeanUtils.copyProperties(eventEditDTO, eventToUpdate, "id", "owner", "users");
                    Event updatedEvent = eventsRepository.save(eventToUpdate);
                    return eventToResponse.convertToResponse(updatedEvent).get();
                });
    }
}
