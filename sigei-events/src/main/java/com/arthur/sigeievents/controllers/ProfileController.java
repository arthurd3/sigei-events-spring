package com.arthur.sigeievents.controllers;

import com.arthur.sigeievents.config.security.model.UserLogged;
import com.arthur.sigeievents.controllers.modelAttributes.ModelAttributes;
import com.arthur.sigeievents.domain.enuns.UserBadges;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProfileController extends ModelAttributes {

    @GetMapping("/userProfile")
    public String userProfilePage(@AuthenticationPrincipal UserLogged userLogged, Model model) {

        if (userLogged != null) {
            List<UserBadges> userBadges = UserBadges.getDistintivosPorPontos(userLogged.getPoints());
            model.addAttribute("userBadges", userBadges);
        }

        model.addAttribute("contentFragment", "pages/user/userProfile");
        model.addAttribute("pageTitle", "Meu Perfil");

        return "layouts/master";
    }
}
