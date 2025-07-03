package com.arthur.sigeievents.usecases.user;

import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class FindUserById {

    private final UsersRepository usersRepository;
    private final UserToResponse userToResponse;

    public Optional<UserResponseDTO> findUserById(Long id) {
        var returnUser = usersRepository.findById(id);

        if (returnUser.isPresent()) {
            return userToResponse.convertToResponse(returnUser.get());
        }

        return Optional.empty();
    }

}
