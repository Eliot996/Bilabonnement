package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.LocationService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

public class CarController {

    private final CarService CAR_SERVICE = new CarService();
    private final LocationService LOCATION_SERVICE = new LocationService();

    @GetMapping("/opret-bil")
    public String getCreateCar(HttpSession session, Model model){
        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());
        model.addAttribute("car", new Car());
        return "create-car";
    }

    @PostMapping("/opret-bil")
    public String createCar(HttpSession session, Model model, @ModelAttribute Car car){
        Car createdCar = CAR_SERVICE.createCar(car);
        return "redirect:/bil/" + createdCar.getId();
    }
}
