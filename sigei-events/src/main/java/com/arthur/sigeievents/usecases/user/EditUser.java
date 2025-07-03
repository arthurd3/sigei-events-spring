package com.arthur.sigeievents.usecases.user;

import com.arthur.sigeievents.controllers.request.UserRequestDTO;
import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.domain.entities.User;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.UserRequestToModel;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EditUser {

    private final UsersRepository usersRepository;
    private final UserToResponse userToResponse;
    @Transactional
    public Optional<UserResponseDTO> editUser(UserRequestDTO userRequestDTO , Long userId) {

        return usersRepository.findById(userId)
                .map(userToUpdate -> {

                    userToUpdate.setUsername(userRequestDTO.username());
                    userToUpdate.setEmail(userRequestDTO.email());
                    userToUpdate.setPhone(userRequestDTO.phone());
                    userToUpdate.setUserType(userRequestDTO.userType());

                    User updatedUser = usersRepository.save(userToUpdate);

                    return userToResponse.convertToResponse(updatedUser).orElse(null);
                });
    }
}
