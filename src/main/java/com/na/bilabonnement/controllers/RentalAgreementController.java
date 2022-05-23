package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.Location;
import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.models.RentalType;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.RentalAgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
public class RentalAgreementController {

    private final CarService CAR_SERVICE = new CarService();
    private final RentalAgreementService RENTAL_AGREEMENT_SERVICE = new RentalAgreementService();

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/opret-lejekontrakt")
    public String getCreateRentalAgreement(HttpSession session, Model model) {



        if (session.getAttribute("userRole") != UserRole.DATA_REGISTRATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole)session.getAttribute("userRole");
         model.addAttribute("userRole", userRole.toString());

        model.addAttribute("cars", CAR_SERVICE.getAllCars()); // todo: make get only relevant cars
        model.addAttribute("rentalAgreement", new RentalAgreement());
        return "create-rental-agreement";
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @PostMapping("/opret-lejekontrakt")
    public String createRentalAgreement(HttpSession session, Model model,
                                        @ModelAttribute RentalAgreement rentalAgreement) {

        if (session.getAttribute("userRole") != UserRole.DATA_REGISTRATION) {
            return "redirect:/logout";
        }

        if (rentalAgreement.getCarId() == -1 ||
            rentalAgreement.getEndDate().isBefore(rentalAgreement.getStartDate())) {

            model.addAttribute("cars", CAR_SERVICE.getAllCars()); // todo: make get only relevant cars
            model.addAttribute("rentalAgreement", new RentalAgreement());

            if (rentalAgreement.getCarId() == -1) {
                model.addAttribute("noCar", true);
            }
            if (rentalAgreement.getEndDate().isBefore(rentalAgreement.getStartDate())) {
                model.addAttribute("dateBeforeStart", true);
            }

            return "create-rental-agreement";
        }

        rentalAgreement = RENTAL_AGREEMENT_SERVICE.create(rentalAgreement);

        return "redirect:/lejekontrakt/" + rentalAgreement.getId();
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/lejekontrakter")
    public String viewAllRentalAgreements(HttpSession session, Model model) {
        if (session.getAttribute("userRole") != UserRole.DATA_REGISTRATION) {
            return "redirect:/logout";
        }

        model.addAttribute("listOfRentalAgreement", RENTAL_AGREEMENT_SERVICE.getAll());

        return "all-rental-agreements";
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @GetMapping("/lejekontrakt/{id}")
    public String getEditRentalAgreement(@PathVariable() int id, HttpSession session, Model model) {
        /*if (session.getAttribute("userRole") != UserRole.DATA_REGISTRATION) {
            return "redirect:/logout";
        }*/

        // brutalization of locations to make the dropdown for rental type work
        Location[] types = {new Location(0, "Limited"), new Location(1, "Unlimited")};
        model.addAttribute("types", types);

        RentalAgreement ra = RENTAL_AGREEMENT_SERVICE.get(id);

        if (ra.getType() == RentalType.LIMITED) {
            ra.setTypeId(0);
        } else {
            ra.setTypeId(1);
        }

        // cannot get date picker to work....

        model.addAttribute("RA", ra);
        model.addAttribute("cars", CAR_SERVICE.getAllCars());

        return "edit-rental-agreement";
    }
}
