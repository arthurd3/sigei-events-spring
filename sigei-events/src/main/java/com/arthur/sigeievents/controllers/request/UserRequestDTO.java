package com.arthur.sigeievents.controllers.request;


import com.arthur.sigeievents.domain.enuns.UserType;

public record UserRequestDTO(Long id,
                             String username,
                             String password,
                             String email,
                             String phone,
                             UserType userType){
}
