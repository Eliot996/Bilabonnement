package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.KeyValueSet;
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
    private final UserService USER_SERVICE = new UserService();
    private final LocationService LOCATION_SERVICE = new LocationService();


    /**
     * @author Mathias(Eliot996)
     * @author Tobias(H4ppyN4p)
     * Get method for creation of a user
     */
    @GetMapping("/opret-bruger")
    public String getCreateUser(HttpSession session, Model model) {
        if (session.getAttribute("userRole") != UserRole.ADMINISTRATOR) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());
        model.addAttribute("user", new User());
        return "create-user";
    }

    /**
     * @author Mathias(Eliot996)
     * Post method for creation of a user
     */
    @PostMapping("/opret-bruger")
    public String createUser(HttpSession session, @ModelAttribute User user) {
        if (session.getAttribute("userRole") != UserRole.ADMINISTRATOR) {
            return "redirect:/logout";
        }

        User createdUser = USER_SERVICE.createUser(user.getUsername(), user.getPassword(), user.getRoleID(), user.getLocationId());

        return "redirect:/bruger/" + createdUser.getId();
    }

    /**
     * @author Mathias(Eliot996)
     * @author Tobias(H4ppyN4p)
     * Return view of all the users on the system
     */
    @GetMapping("/brugere")
    public String getAllUsers(HttpSession session, Model model) {
        if (session.getAttribute("userRole") != UserRole.ADMINISTRATOR) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("listOfUsers", USER_SERVICE.getAllUsers());

        return "all-users";
    }

    /**
     * @author Mathias(Eliot996)
     * @author Tobias(H4ppyN4p)
     */
    @GetMapping("/bruger/{userID}")
    public String getEditUser(HttpSession session, @PathVariable() int userID, Model model) {
        if (session.getAttribute("userRole") != UserRole.ADMINISTRATOR) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");

        User user = USER_SERVICE.getUser(userID);

        // setting the roleId to make the dropdown work
        switch (user.getRole()) {
            case DATA_REGISTRATION:
                user.setRoleID(0);
                break;
            case DAMAGE_AND_RECTIFICATION:
                user.setRoleID(1);
                break;
            case BUSINESS_DEVELOPER:
                user.setRoleID(2);
                break;
            case ADMINISTRATOR:
                user.setRoleID(3);
                break;
        }

        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("user", user);
        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());

        // brutalized to make the dropdown work...
        KeyValueSet[] roles = {
                new KeyValueSet(0, "Dataregistrering"),
                new KeyValueSet(1, "Skade og Udbedring"),
                new KeyValueSet(2, "Forretningsudvikler"),
                new KeyValueSet(3, "Administrator")
        };
        model.addAttribute("roles", roles);

        return "edit-user";
    }

    /**
     * @author Mathias(Eliot996)
     */
    @PostMapping("/bruger/{userID}")
    public String editUser(HttpSession session, @PathVariable() int userID, @ModelAttribute User user) {
        if (session.getAttribute("userRole") != UserRole.ADMINISTRATOR) {
            return "redirect:/logout";
        }

        if (user.getPassword().equals("")) {
            USER_SERVICE.updateUser(userID, user.getUsername(), user.getRoleID(), user.getLocationId());
        } else {
            USER_SERVICE.updateUser(userID, user.getUsername(), user.getPassword(), user.getRoleID(), user.getLocationId());
        }

        return "redirect:/bruger/" + userID;
    }

    /**
     * @author Mathias(Eliot996)
     * @author Sofia
     */
    @GetMapping("/bruger/{userID}/slet")
    public String deleteUser(HttpSession session, @PathVariable() int userID) {
        if (session.getAttribute("userRole") != UserRole.ADMINISTRATOR) {
            return "redirect:/logout";
        }

        USER_SERVICE.deleteUser(userID);
        return "redirect:/brugere";
    }


}
