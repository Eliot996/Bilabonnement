package com.na.bilabonnement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/*
@Author Sofia
 */
@Controller
public class MainController {

    @GetMapping("/")
        public String landingPage(){
        return "landingpage";
    }

}
