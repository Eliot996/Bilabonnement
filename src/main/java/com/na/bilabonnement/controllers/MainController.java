package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/*
@Author Sofia
 */
@Controller
public class MainController {
    private final UserService USER_SERVICE = new UserService();



    @GetMapping("/")
        public String landingPage(HttpSession session, Model model){

        model.addAttribute("user", new User());
        return "landingpage";
    }

   @PostMapping("/login")
    public String landingPage(WebRequest dataFromForm, HttpSession session){


    //boolean validity = USER_SERVICE.checkLogin(dataFromForm.getParameter("uname"), dataFromForm.getParameter("psw"));
    User user = USER_SERVICE.login(dataFromForm.getParameter("uname"), dataFromForm.getParameter("psw"));

       System.out.println(dataFromForm.getParameter("uname"));
       System.out.println(dataFromForm.getParameter("psw"));

    if (user != null)
    {


        session.setAttribute("userID", user.getId());
        session.setAttribute("userRole", user.getRole());
        return "redirect:/success";
    }

    else
    {
        return "redirect:/";
    }
   }

   @GetMapping("/fail")
       public String fail()
   {
           return "fail";
   }

    @GetMapping("/success")
    public String success()
    {
        return "success";
    }
}
