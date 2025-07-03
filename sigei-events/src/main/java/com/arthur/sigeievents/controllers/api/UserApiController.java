package com.arthur.sigeievents.controllers.api;

import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.usecases.user.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;



@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserApiController {

    private FindUserById findUserById;


    @GetMapping("/findUser/{id}")
    public ResponseEntity<UserResponseDTO> findUser(@PathVariable Long id) {

        var userFind = findUserById.findUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ResponseEntity.status(HttpStatus.FOUND).body(userFind);
    }






}
