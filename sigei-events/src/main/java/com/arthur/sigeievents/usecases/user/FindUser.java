package com.arthur.sigeievents.usecases.user;

import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.UserRequestToModel;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindUser {

    private final UsersRepository usersRepository;

    public Optional<User> findUser(Long id){

        if (id == null) {
            return Optional.empty();
        }

        return usersRepository.findById(id);
    }
}
