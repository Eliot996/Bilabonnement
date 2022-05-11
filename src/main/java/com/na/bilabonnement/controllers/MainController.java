package com.na.bilabonnement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    /*
    @Author Sofia
    */
    @GetMapping("/")
        public String landingPage(){
        return "landingpage";
    }
}
