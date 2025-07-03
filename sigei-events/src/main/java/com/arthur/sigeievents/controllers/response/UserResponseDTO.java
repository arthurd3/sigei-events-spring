package com.arthur.sigeievents.controllers.response;

import com.arthur.sigeievents.domain.enuns.UserType;

import java.util.List;

public record UserResponseDTO(Long id,
                              String username,
                              String email,
                              String phone,
                              UserType userType,
                              List<EventResponseDTO> participatingEvents,
                              List<EventResponseDTO> ownedEvents,
                              int points) {
}

