package com.arthur.sigeievents.usecases.events;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.repositories.EventsRepository;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor

public class FindEvent {

    private final EventsRepository eventsRepository;
    private EventToResponse  eventToResponse;

    public Optional<EventResponseDTO> findEventResponse(Long id){

        var eventReturn = eventsRepository.findById(id);

        if(eventReturn.isEmpty()){
            return Optional.empty();
        }

        return eventToResponse.convertToResponse(eventReturn.get());
    }

}
