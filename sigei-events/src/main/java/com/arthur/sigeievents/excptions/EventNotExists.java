package com.arthur.sigeievents.excptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotExists extends RuntimeException {

    public EventNotExists(String message) {
        super(message);
    }

}
