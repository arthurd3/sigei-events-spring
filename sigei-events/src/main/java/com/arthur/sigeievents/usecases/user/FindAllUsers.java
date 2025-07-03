package com.arthur.sigeievents.usecases.user;

import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.repositories.UsersRepository;
import com.arthur.sigeievents.usecases.mappers.UserRequestToModel;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllUsers {

    private final UsersRepository usersRepository;
    private final UserToResponse userToResponse;


    public List<UserResponseDTO> findAllUsers() {

        return usersRepository.findAll()
                .stream()
                .map(user -> userToResponse.convertToResponse(user).get())
                .collect(Collectors.toList());

    }
}
