package com.arthur.sigeievents.usecases.mappers;

import com.arthur.sigeievents.controllers.request.UserRequestDTO;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.domain.enuns.UserType;
import com.arthur.sigeievents.usecases.user.FindUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


public class UserRequestToModel {

    @Autowired
    private FindUser findUser;

    public User convertToModel(UserRequestDTO userRequestDTO) {
        var userExists = findUser.findUser(userRequestDTO.id());

        return userExists.orElseGet(() -> new User(
                userRequestDTO.id(),
                userRequestDTO.username(),
                userRequestDTO.password(),
                userRequestDTO.email(),
                userRequestDTO.phone(),
                UserType.PARTICIPANT,
                new ArrayList<>(),
                new ArrayList<>(),
                0
        ));

    }

}
