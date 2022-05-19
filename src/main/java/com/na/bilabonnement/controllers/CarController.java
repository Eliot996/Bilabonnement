package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CarController {
/*
@Author Sofia
 */
    private final CarService CAR_SERVICE = new CarService();
    private final LocationService LOCATION_SERVICE = new LocationService();

    @GetMapping("/opret-bil")
    public String getCreateCar(HttpSession session, Model model){
        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());
        model.addAttribute("car", new Car());
        return "create-car";
    }
/*
@Author Sofia
 */
    @PostMapping("/opret-bil")
    public String createCar(HttpSession session, Model model, @ModelAttribute Car car){
        Car createdCar = CAR_SERVICE.createCar(car);
        return "redirect:/bil/" + createdCar.getId();
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/biler")
    public String viewAllCars(HttpSession session, Model model) {
        model.addAttribute("listOfCars", CAR_SERVICE.getAllCars());

        return "all-cars";
    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @GetMapping("/bil/{carID}")
    public String getEditCar(HttpSession session, @PathVariable() int carID, Model model){

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole!=UserRole.BUSINESS_DEVELOPER){
            return "redirect:/";
        }

        Car car = CAR_SERVICE.getCar(carID);

        model.addAttribute("car", car);
        model.addAttribute("locations",LOCATION_SERVICE.getAllLocations());



        return "edit-car";
    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @PostMapping("/bil/{carID}")
    public String editCar(HttpSession session, @ModelAttribute Car car, @PathVariable int carID){

        CAR_SERVICE.updateCar(car.getChassisNumber(), car.getId(), car.getStatus(), car.getMake(), car.getModel(), car.getTrimLevel(), car.getScrapPrice(), car.getRegistrationFee(), car.getCo2Emission(), car.getKilometersDriven(), car.getDamage(), car.getColour(), car.getFuelType(), car.getLocationId());

        return "redirect:/biler";

    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @GetMapping("/bil/{carID}/slet")
    public String deleteCar(HttpSession session, @PathVariable() int carID){

        CAR_SERVICE.deleteCar(carID);

        return "redirect:/biler";
    }
}
