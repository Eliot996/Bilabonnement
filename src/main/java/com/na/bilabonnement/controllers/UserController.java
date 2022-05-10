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

    private UserService userService = new UserService();
    private LocationService locationService = new LocationService();

    /**
    *  @author Mathias(Eliot996)
    */
    @GetMapping("/create-user")
    public String getCreateUser(HttpSession session, Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        model.addAttribute("user", new User());
        return "create-user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "administratorpage";
    }
    /**
    *  @author Mathias(Eliot996)
    */
    @PostMapping("/create-user")
    public String CreateUser(HttpSession session, @ModelAttribute User user) {
        User createdUser = userService.createUser(user.getUsername(), user.getPassword(), user.getRoleID(), user.getLocationId());

        return "redirect:/user/" + createdUser.getId();
    }

    /*
    @Author Sofia
     */
    @GetMapping("/dataregistrering")
    public String dataregistration(){
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
    public String businessdeveloper() {
        return "businessdeveloper";
    }
}
