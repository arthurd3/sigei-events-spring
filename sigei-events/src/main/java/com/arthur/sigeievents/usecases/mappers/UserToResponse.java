package com.arthur.sigeievents.usecases.mappers;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.domain.enuns.UserType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserToResponse {

    @Autowired
    private EventToResponse eventToResponse;

    public Optional<UserResponseDTO> convertToResponse(User user) {

        var participatingEvents = eventToResponse.convertListToResponse(user.getParticipatingEvents());
        var ownerdEvents = eventToResponse.convertListToResponse(user.getOwnedEvents());

        var convertUser = new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getUserType(),
                participatingEvents,
                ownerdEvents,
                user.getPoints()
        );

        return Optional.of(convertUser);
    }
}
