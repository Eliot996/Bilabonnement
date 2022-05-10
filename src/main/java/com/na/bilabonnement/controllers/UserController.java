package com.na.bilabonnement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.na.bilabonnement.models.User;
import com.na.bilabonnement.services.LocationService;
import com.na.bilabonnement.services.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    /*
    @Author Sofia
     */

    private final UserService USER_SERVICE = new UserService();
    private final LocationService LOCATION_SERVICE = new LocationService();

    /**
    *  @author Mathias(Eliot996)
    */
    @GetMapping("/create-user")
    public String getCreateUser(HttpSession session, Model model) {
        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());
        model.addAttribute("user", new User());
        return "create-user";
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @PostMapping("/create-user")
    public String createUser(HttpSession session, @ModelAttribute User user) {
        User createdUser = USER_SERVICE.createUser(user.getUsername(), user.getPassword(), user.getRoleID(), user.getLocationId());

        return "redirect:/user/" + createdUser.getId();
    }

    @GetMapping("/admin")
    public String admin(){
        return "administratorpage";
    }

    /*
    @Author Sofia
     */
    @GetMapping("/dataregistrering")
    public String dataRegistration(){
        return "dataregistration";
    }

    /*
    @Author Sofia
     */
    @GetMapping("skade_og_udbedring")
    public String damageAndRectification(){
        return "damageAndRectification";
    }

    @GetMapping("/forretningsudvikler")
    public String businessDeveloper() {
        return "businessdeveloper";
    }
}
