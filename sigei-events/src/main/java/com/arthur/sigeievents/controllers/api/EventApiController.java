package com.arthur.sigeievents.controllers.api;

import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.usecases.events.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/event/")
public class EventApiController {

    private FindOwnedEvents findOwnedEvents;
    private FindJoinedEvents findJoinedEvents;
    private FindEvent findEvent;
    private DeleteEvent deleteEvent;

    @GetMapping("/findOwnedEvents/{id}")
    public ResponseEntity<List<EventResponseDTO>> findOwnedEvent(@PathVariable(name = "id") Long userId){

        var foundEvents = findOwnedEvents.findOwnedEvents(userId);

        return ResponseEntity.ok(foundEvents);

    };

    @GetMapping("/findJoinedUserEvents/{id}")
    public ResponseEntity<?> findJoinedUserEvent(@PathVariable(name = "id") Long userId){
        var foundEvents = findJoinedEvents.findJoinedEvents(userId);

        return ResponseEntity.ok(foundEvents);
    };



    @GetMapping("/findEvent/{id}")
    public ResponseEntity<?> findEvent(@PathVariable(name = "id") Long userId){

        var eventResponseDTO = findEvent.findEventResponse(userId);

        return ResponseEntity.status(HttpStatus.FOUND).body(eventResponseDTO);

    };

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable(name = "id") Long eventId){
        var deletedEvent = deleteEvent.deleteEvent(eventId)
                .orElseThrow();

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletedEvent);
    };



}
