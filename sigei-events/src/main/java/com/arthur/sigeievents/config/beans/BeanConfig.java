package com.arthur.sigeievents.config.beans;

import com.arthur.sigeievents.usecases.mappers.EventRequestToModel;
import com.arthur.sigeievents.usecases.mappers.EventToResponse;
import com.arthur.sigeievents.usecases.mappers.UserRequestToModel;
import com.arthur.sigeievents.usecases.mappers.UserToResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {

    @Bean
    public UserRequestToModel userToModel() {
        return new UserRequestToModel();
    }

    @Bean
    public UserToResponse userToResponse() {
        return new UserToResponse();
    }

    @Bean
    EventRequestToModel eventRequestToModel() {
        return new EventRequestToModel();
    }

    @Bean
    EventToResponse eventToResponse() {
        return new EventToResponse();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
