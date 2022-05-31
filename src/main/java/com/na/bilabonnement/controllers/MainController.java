package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private final UserService USER_SERVICE = new UserService();

    /**
     * @author Tobias Arboe
     */
    @GetMapping("/")
    public String getLandingPage(HttpSession session, Model model) {

        if (session.getAttribute("loginSuccess") == null) {
            session.setAttribute("loginSuccess", "none");
        }
        model.addAttribute("loginValidity", session.getAttribute("loginSuccess"));

        if (session.getAttribute("userID") != null) {
            return "redirect:/home";
        }

        model.addAttribute("user", new User());
        return "landingpage";
    }

    /**
     * @author Tobias Arboe
     */
    @PostMapping("/login")
    public String landingPage(WebRequest dataFromForm, HttpSession session) {

        User user = USER_SERVICE.login(dataFromForm.getParameter("uname"), dataFromForm.getParameter("psw"));

        if (user != null) {
            session.setAttribute("userID", user.getId());
            session.setAttribute("userRole", user.getRole());
            session.setAttribute("loginSuccess", "success");
            return "redirect:/home";
        } else {
            session.setAttribute("loginSuccess", "fail");
            return "redirect:/";
        }
    }

    /*
    @Author Sofia
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /**
     * @author Tobias Arboe
     */
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole == UserRole.ADMINISTRATOR) {
            model.addAttribute("userRole", userRole.toString());
            return "administratorpage";
        } else if (userRole == UserRole.BUSINESS_DEVELOPER) {
            model.addAttribute("userRole", userRole.toString());
            return "businessdeveloper";
        } else if (userRole == UserRole.DAMAGE_AND_RECTIFICATION) {
            model.addAttribute("userRole", userRole.toString());
            return "damageAndRectification";
        } else if (userRole == UserRole.DATA_REGISTRATION) {
            model.addAttribute("userRole", userRole.toString());
            return "dataregistration";
        }

        return "redirect:/";
    }
}
