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
import java.util.List;

@Controller
public class CarController {
    private final CarService CAR_SERVICE = new CarService();
    private final LocationService LOCATION_SERVICE = new LocationService();
    private final KeyValueSet[] CAR_STATUS_SET = {
            new KeyValueSet(1, "klar til udlejning"),
            new KeyValueSet(2, "Klar til levering"),
            new KeyValueSet(3, "Udlejet"),
            new KeyValueSet(4, "Tilbage fra udlejning"),
            new KeyValueSet(5, "Klar til salg")
    };

    /**
     *  @author Sofia
     */
    @GetMapping("/opret-bil")
    public String getCreateCar(HttpSession session, Model model){
        if ( session.getAttribute("userRole") == null) {
            return "redirect:/logout";
        }

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
        if ( session.getAttribute("userRole") == null) {
            return "redirect:/logout";
        }

        Car createdCar = CAR_SERVICE.createCar(car);

        return "redirect:/bil/" + createdCar.getId();
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/biler")
    public String viewAllCars(HttpSession session, Model model) {
        if ( session.getAttribute("userRole") == null) {
            return "redirect:/logout";
        }

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
        if ( session.getAttribute("userRole") == null) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        Car car = CAR_SERVICE.getCar(carID);

        car.setCarStatusId(CAR_SERVICE.getCarStatusValue(car));
        model.addAttribute("statuses", CAR_STATUS_SET);

        model.addAttribute("car", car);
        model.addAttribute("locations",LOCATION_SERVICE.getAllLocations());



        return "edit-car";
    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @PostMapping("/bil/{carID}")
    public String editCar(HttpSession session, @ModelAttribute Car car, @PathVariable int carID){
        if ( session.getAttribute("userRole") == null) {
            return "redirect:/logout";
        }

        CAR_SERVICE.updateCar(carID,
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
        if ( session.getAttribute("userRole") == null) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole);

        CAR_SERVICE.deleteCar(carID);

        return "redirect:/biler";
    }

    /**
     *  @author Arboe(H4ppyN4p)
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/biler/data")
    public String carData(HttpSession session, Model model){
        if (session.getAttribute("userRole") != UserRole.BUSINESS_DEVELOPER) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        List<Car> listOfCars = CAR_SERVICE.getCarsByStatus(CarStatus.RENTED);

        model.addAttribute("listOfRentedCars", listOfCars);
        model.addAttribute("numberOfRentedCars", listOfCars.size());
        model.addAttribute("totalPriceOfRentedCars", CAR_SERVICE.getPriceOfRentedCars());

        return "car-data";
    }
}
