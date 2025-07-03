package com.arthur.sigeievents.controllers;

import com.arthur.sigeievents.controllers.modelAttributes.ModelAttributes;
import com.arthur.sigeievents.controllers.request.UserRequestDTO;
import com.arthur.sigeievents.usecases.user.DeleteUser;
import com.arthur.sigeievents.usecases.user.EditUser;
import com.arthur.sigeievents.usecases.user.FindUserById;
import com.arthur.sigeievents.usecases.user.RegisterUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
public class UserController extends ModelAttributes {

    private RegisterUser registerUser;
    private FindUserById findUserById;
    private EditUser editUser;
    private DeleteUser deleteUser;

    @PostMapping("/registerUser")
    public String registerUserPage(@ModelAttribute UserRequestDTO userDTO) {
        var returnUser = registerUser.registerUser(userDTO);

        if (returnUser.isPresent()) {
            return "redirect:/login";
        }

        return "redirect:/register";
    }

    @GetMapping("/admin/showUsers")
    public String showAllUsersPage(Model model)
    {
        model.addAttribute("contentFragment", "pages/admin/showUsers");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }


    @PostMapping("/editUser/{id}")
    public String editUser(@PathVariable Long id , @ModelAttribute UserRequestDTO userDTO){
        var returnEvent = editUser.editUser(userDTO,id);

        if (returnEvent.isPresent()) {
            return "redirect:/admin/showUsers";
        }
        return "redirect:/admin/showUsers?erro-ao-editar";
    };


    @GetMapping("/edit-user/{id}")
    public String editUserPage(@PathVariable Long id, Model model) {
        var returnedEvent = findUserById.findUserById(id).orElseThrow();
        model.addAttribute("UserEdit", new UserRequestDTO(returnedEvent.id() , returnedEvent.username() , null , returnedEvent.email() , returnedEvent.phone() , returnedEvent.userType()));
        model.addAttribute("contentFragment", "pages/admin/editUser");
        model.addAttribute("pageTitle", "Zezin Eventos , confira os melhores Eventos");
        return "layouts/master";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {

        var deletedUser = deleteUser.deleteUser(id);

        if(deletedUser.isPresent()) {
            return "redirect:/admin/showUsers?success";
        }
        return "redirect:/admin/showUsers?error-delete";


    }

}
