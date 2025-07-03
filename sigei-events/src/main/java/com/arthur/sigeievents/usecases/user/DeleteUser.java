package com.arthur.sigeievents.usecases.user;

import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeleteUser {

    private final UsersRepository userRepository;
    private final UserToResponse userToResponse;

    @Transactional
    public Optional<UserResponseDTO> deleteUser(Long id) {

        var returnUser = userRepository.findById(id);

        if (returnUser.isPresent()) {
            userRepository.deleteById(id);
            return userToResponse.convertToResponse(returnUser.get());
        }

        return Optional.empty();
    }

}
