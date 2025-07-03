package com.arthur.sigeievents.usecases.events;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.repositories.EventsRepository;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class FindOwnedEvents {

    private final EventsRepository eventsRepository;
    private EventToResponse eventToResponse;

    public List<EventResponseDTO> findOwnedEvents(Long id) {
        return eventToResponse.convertListToResponse(eventsRepository.findByOwnerUser_Id(id));
    }

}
