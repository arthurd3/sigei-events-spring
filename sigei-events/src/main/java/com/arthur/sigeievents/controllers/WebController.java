package com.arthur.sigeievents.controllers;

import com.arthur.sigeievents.config.security.model.UserLogged;
import com.arthur.sigeievents.controllers.modelAttributes.ModelAttributes;
import com.arthur.sigeievents.controllers.request.UserRequestDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController extends ModelAttributes {

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("contentFragment", "pages/home");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("contentFragment", "pages/login");
        model.addAttribute("pageTitle", "Login");
        return "layouts/master";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("UserRequestDTO", new UserRequestDTO(null,null,null,null,null , null));
        model.addAttribute("contentFragment", "pages/register");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }

    @GetMapping("/myEvents")
    public String myEventsPage(@AuthenticationPrincipal UserLogged userLogged , Model model) {

        boolean hasPermission = userLogged.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_ADMIN") || role.equals("ROLE_ORGANIZER"));

        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");

        if (hasPermission) {
            model.addAttribute("contentFragment", "pages/myEvents");
        } else {
            model.addAttribute("contentFragment", "pages/becomeOrganizer");
        }

        return "layouts/master";
    }


    @GetMapping("/myTickets")
    public String myTicktsPage(Model model) {
        model.addAttribute("contentFragment", "pages/myTickets");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }

    @GetMapping("/becomeOrganizer")
    public String becomeOrganizerPage(Model model) {
        model.addAttribute("contentFragment", "pages/becomeOrganizer");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }



}
