package com.arthur.sigeievents.usecases.events;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.repositories.EventsRepository;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DeleteEvent {

    private final EventsRepository eventsRepository;
    private EventToResponse eventToResponse;


    public Optional<EventResponseDTO> deleteEvent(Long id) {

        var eventDeleted = eventsRepository.findById(id);

        if(eventDeleted.isPresent())
        {
            var deletedEvent = eventDeleted.get();

            deletedEvent.getEventSpeakers().clear();

            for (User participant : new ArrayList<>(deletedEvent.getEventParticipants())) {
                participant.getParticipatingEvents().remove(deletedEvent);
            }

            eventsRepository.delete(deletedEvent);

            return eventToResponse.convertToResponse(deletedEvent);
        }

        return Optional.empty();


    }
}
