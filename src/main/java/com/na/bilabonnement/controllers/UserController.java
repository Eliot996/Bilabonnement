package com.na.bilabonnement.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/admin")
    public String admin(){
        return "administratorpage";
    }

    @GetMapping("/dataregistrering")
    public String dataregistration(){
        return "dataregistration";
    }

    @GetMapping("skade_og_udbedring")
    public String damageAndRectification(){
        return "damageAndRectification";
    }

    @GetMapping("/forretningsudvikler")
    public String businessdeveloper(){
        return "businessdeveloper";
    }
}
