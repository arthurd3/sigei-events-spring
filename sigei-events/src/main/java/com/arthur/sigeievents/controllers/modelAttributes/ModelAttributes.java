package com.arthur.sigeievents.controllers.modelAttributes;

import com.arthur.sigeievents.config.security.model.UserLogged;
import com.arthur.sigeievents.controllers.response.EventResponseDTO;
import com.arthur.sigeievents.controllers.response.UserResponseDTO;
import com.arthur.sigeievents.domain.enuns.UserBadges;
import com.arthur.sigeievents.domain.enuns.UserType;
import com.arthur.sigeievents.usecases.events.FindAllEvents;
import com.arthur.sigeievents.usecases.user.FindAllUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.List;

@Controller
public class ModelAttributes {

    @Autowired
    private FindAllUsers findAllUsers;
    @Autowired
    private FindAllEvents findAllEvents;

    public ModelAttributes() {
    }

    @ModelAttribute("allUserTypes")
    public UserType[] allUserTypes() {
        return UserType.values();
    }


    @ModelAttribute("allUsers")
    public List<UserResponseDTO> allUsers() {
        return findAllUsers.findAllUsers();
    }

    @ModelAttribute("allEvents")
    public List<EventResponseDTO> allEvents() {
        return findAllEvents.findAllEvents();
    }

    @ModelAttribute("userName")
    public String addUserNameToModel(@AuthenticationPrincipal UserLogged userDetails) {
        if (userDetails != null) {
            return userDetails.getUsername();
        }
        return "";
    }

    @ModelAttribute("userPoints")
    public int addUserPointsToModel(@AuthenticationPrincipal UserLogged userDetails) {
        if (userDetails != null) {
            return userDetails.getPoints();
        }
        return 0;
    }

    @ModelAttribute("userId")
    public Long addUserId(@AuthenticationPrincipal UserLogged userDetails) {
        if (userDetails != null) {
            return userDetails.getId();
        }
        return null;
    }

    @ModelAttribute("eventosDestaque")
    public List<EventResponseDTO> addAllEmphasisEvents() {
        List<EventResponseDTO> eventResponseDTOs = this.findAllEvents();
        Collections.shuffle(eventResponseDTOs);
        return eventResponseDTOs;
    }

    @ModelAttribute("eventos")
    public List<EventResponseDTO> addAllEvents() {
        List<EventResponseDTO> eventResponseDTOs = this.findAllEvents();
        Collections.shuffle(eventResponseDTOs);
        return eventResponseDTOs;
    }

    public List<EventResponseDTO> findAllEvents()
    {
        var foundEvents = findAllEvents.findAllEvents();

        if(foundEvents.isEmpty()){
            return List.of();
        }
        return foundEvents;
    }
}
