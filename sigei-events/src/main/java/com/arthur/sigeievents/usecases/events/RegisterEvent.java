package com.arthur.sigeievents.usecases.events;

import com.arthur.sigeievents.controllers.request.EventRequestDTO;
import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.repositories.EventsRepository;
import com.arthur.sigeievents.usecases.mappers.EventRequestToModel;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import com.arthur.sigeievents.usecases.user.FindUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RegisterEvent {

    private final EventsRepository eventsRepository;
    private EventRequestToModel eventRequestToModel;
    private EventToResponse eventToResponse;
    private FindUser findUser;

    public Optional<EventResponseDTO> registerEvent(EventRequestDTO eventDTO , Long ownerId){

        var ownerUser = findUser.findUser(ownerId).get();
        var eventSave = eventRequestToModel.convertToModel(eventDTO , ownerUser);

        if(ownerUser == null || eventSave == null){
            return Optional.empty();
        }

        eventsRepository.save(eventSave);
        return eventToResponse.convertToResponse(eventSave);

    }

}
