package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;
import com.na.bilabonnement.models.KeyValueSet;
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
    private final KeyValueSet[] CAR_STATUS_SET = {
            new KeyValueSet(1, "klar til udlejning"),
            new KeyValueSet(2, "Klar til levering"),
            new KeyValueSet(3, "Udlejet"),
            new KeyValueSet(4, "Tilbage fra udlejning"),
            new KeyValueSet(5, "Klar til salg")
    };

    @GetMapping("/opret-bil")
    public String getCreateCar(HttpSession session, Model model){
        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("locations", LOCATION_SERVICE.getAllLocations());
        model.addAttribute("car", new Car());
        return "create-car";
    }

    /**
     *  @author Sofia
     *  @author Mathias(Eliot996)
     */
    @PostMapping("/opret-bil")
    public String createCar(HttpSession session, @ModelAttribute Car car){
        car.setStatus(CarStatus.READY_TO_BE_RENTED);

        Car createdCar = CAR_SERVICE.createCar(car);

        return "redirect:/bil/" + createdCar.getId();
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/biler")
    public String viewAllCars(HttpSession session, Model model) {
        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("listOfCars", CAR_SERVICE.getAllCars());

        return "all-cars";
    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @GetMapping("/bil/{carID}")
    public String getEditCar(HttpSession session, @PathVariable() int carID, Model model){

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole != UserRole.BUSINESS_DEVELOPER){
            return "redirect:/biler";
        }
        model.addAttribute("userRole", userRole.toString());

        Car car = CAR_SERVICE.getCar(carID);

        car.setCarStatusId(CAR_SERVICE.getCarStatusValue(car));
        model.addAttribute("statuses", CAR_STATUS_SET);

        System.out.println(model.getAttribute("currentStatusValue"));

        model.addAttribute("car", car);
        model.addAttribute("locations",LOCATION_SERVICE.getAllLocations());



        return "edit-car";
    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @PostMapping("/bil/{carID}")
    public String editCar(HttpSession session, @ModelAttribute Car car, @PathVariable int carID){

        CAR_SERVICE.updateCar(car.getId(),
                car.getChassisNumber(),
                CarStatus.values()[car.getCarStatusId() - 1],
                car.getMake(),
                car.getModel(),
                car.getTrimLevel(),
                car.getCarPrice(),
                car.getScrapPrice(),
                car.getRegistrationFee(),
                car.getCo2Emission(),
                car.getKilometersDriven(),
                car.getDamage(),
                car.getColour(),
                car.getFuelType(),
                car.getLocationId());

        return "redirect:/biler";

    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @GetMapping("/bil/{carID}/slet")
    public String deleteCar(HttpSession session, @PathVariable() int carID, Model model){

        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole);

        CAR_SERVICE.deleteCar(carID);

        return "redirect:/biler";
    }

    @GetMapping("/biler/data")
    public String carData(HttpSession session, Model model){

        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("listOfRentedCars", CAR_SERVICE.getRentedCars());
        model.addAttribute("numberOfRentedCars", CAR_SERVICE.getRentedCars().size());
        model.addAttribute("totalPriceOfRentedCars", CAR_SERVICE.getPriceOfRentedCars());

        return "car-data";
    }
}
