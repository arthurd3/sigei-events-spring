package com.arthur.sigeievents.usecases.user;

import com.arthur.sigeievents.controllers.request.UserRequestDTO;
import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.UserRequestToModel;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RegisterUser {

    private final UsersRepository usersRepository;
    private final UserToResponse userToResponse;
    private final UserRequestToModel userRequestToModel;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserResponseDTO> registerUser(UserRequestDTO userRequestDTO) {

        var userSave = userRequestToModel.convertToModel(userRequestDTO);

        String encodedPassword = passwordEncoder.encode(userSave.getPassword());
        userSave.setPassword(encodedPassword);

        return userToResponse.convertToResponse(usersRepository.save(userSave));

    }
}
