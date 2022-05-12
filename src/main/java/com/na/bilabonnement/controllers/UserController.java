package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.Location;
import com.na.bilabonnement.models.UserRole;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.na.bilabonnement.models.User;
import com.na.bilabonnement.services.LocationService;
import com.na.bilabonnement.services.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
     *  Get method for creation of a user
     */
    @GetMapping("/opret-bruger")
    public String getCreateUser(HttpSession session, Model model) {
        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());
        model.addAttribute("user", new User());
        return "create-user";
    }

    /**
     *  @author Mathias(Eliot996)
     *  Post method for creation of a user
     */
    @PostMapping("/opret-bruger")
    public String createUser(HttpSession session, @ModelAttribute User user) {
        User createdUser = USER_SERVICE.createUser(user.getUsername(), user.getPassword(), user.getRoleID(), user.getLocationId());

        return "redirect:/user/" + createdUser.getId();
    }

    /**
     *  @author Mathias(Eliot996)
     *  return view of all the users on the system
     */
    @GetMapping("/brugere")
    public String getAllUsers(HttpSession session, Model model) {

        model.addAttribute("listOfUsers", USER_SERVICE.getAllUsers());

        return "all-users";
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/bruger/{userID}")
    public String getEditUser(HttpSession session, @PathVariable() int userID, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole!=UserRole.ADMINISTRATOR){
            return "redirect:/";
        }

        User user = USER_SERVICE.getUser(userID);

        // setting the roleId to make the dropdown work
        switch (user.getRole()) {
            case DATA_REGISTRATION:         user.setRoleID(0);  break;
            case DAMAGE_AND_RECTIFICATION:  user.setRoleID(1);  break;
            case BUSINESS_DEVELOPER:        user.setRoleID(2);  break;
            case ADMINISTRATOR:             user.setRoleID(3);  break;
        }

        model.addAttribute("user", user);
        model.addAttribute("locations",LOCATION_SERVICE.getAllLocations());

        // brutalized to make the dropdown work...
        Location[] roles = {
                new Location(0, "Dataregistrering"),
                new Location(1, "Skade og Udbedring"),
                new Location(2, "Forretningsudvikler"),
                new Location(3, "Administrator")
        };
        model.addAttribute("roles", roles);

        return "edit-user";
    }

    @PostMapping("/bruger/{userID}")
    public String editUser(HttpSession session, @PathVariable() int userID, @ModelAttribute User user){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole!=UserRole.ADMINISTRATOR){
            return "redirect:/";
        }

        if (user.getPassword().equals("")) {
            USER_SERVICE.updateUser(userID,user.getUsername(), user.getRoleID(), user.getLocationId());
        } else {
            USER_SERVICE.updateUser(userID,user.getUsername(), user.getPassword(), user.getRoleID(), user.getLocationId());
        }

        return "redirect:/bruger/" + userID;
    }

    /*
    @Author Sofia
     */
    @GetMapping("/bruger/{userID}/slet")
    public String deleteUser(HttpSession session, @PathVariable() int userID){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole!=UserRole.ADMINISTRATOR){
            return "redirect:/logout";
        }
        USER_SERVICE.deleteUser(userID);
        return "redirect:/brugere";
    }



    @GetMapping ("/home")
    public String home(HttpSession session){
        UserRole userRole = (UserRole)session.getAttribute("userRole");
        if (userRole == UserRole.ADMINISTRATOR){
            return "administratorpage";
        } else if (userRole == UserRole.BUSINESS_DEVELOPER)
            return "businessdeveloper";
        else if (userRole == UserRole.DAMAGE_AND_RECTIFICATION)
            return "damageAndRectification";
        else if (userRole == UserRole.DATA_REGISTRATION)
            return "dataregistration";
        return "redirect:/home";
    }

    /*
    @Author Lasse
    */
    /*@GetMapping("/admin")
    public String admin(){
        return "administratorpage";
    }

    *//*
    @Author Sofia
     *//*
    @GetMapping("/dataregistrering")
    public String dataregistration(){
        return "dataregistration";
    }

    *//*
    @Author Sofia
     *//*
    @GetMapping("/skade_og_udbedring")
    public String damageAndRectification(){
        return "damageAndRectification";
    }

    *//*
    @Author Lasse
    *//*
    @GetMapping("/forretningsudvikler")
    public String businessdeveloper(){
        return "businessdeveloper";
    }*/


}
