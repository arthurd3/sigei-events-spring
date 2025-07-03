package com.arthur.sigeievents.usecases.events;

import com.arthur.sigeievents.controllers.request.EventRequestDTO;
import com.arthur.sigeievents.domain.entities.Event;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.repositories.EventsRepository;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.EventRequestToModel;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import com.arthur.sigeievents.usecases.user.FindUser;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JoinEvent {

    private final EventsRepository eventsRepository;
    private final UsersRepository usersRepository;
    private FindUser findUser;

    @Transactional
    public boolean joinEvent(Long id , Long eventId){

        var userOptional = findUser.findUser(id);
        var eventOptional = eventsRepository.findById(eventId);

        if (userOptional.isEmpty() || eventOptional.isEmpty()) {
            return false;
        }

        User userToJoin = userOptional.get();
        Event eventToJoin = eventOptional.get();
        User ownerEvent = eventToJoin.getOwnerUser();

        boolean successJoin = eventToJoin.addParticipant(userToJoin);

        if(!successJoin){
            return false;
        }

        ownerEvent.setPoints(ownerEvent.getPoints() + 250);
        userToJoin.setPoints(userToJoin.getPoints() + 100);

        eventsRepository.save(eventToJoin);
        usersRepository.save(userToJoin);
        usersRepository.save(ownerEvent);

        return true;



    }

}
