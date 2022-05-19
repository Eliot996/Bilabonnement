package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.RentalAgreementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class RentalAgreementController {

    private final CarService CAR_SERVICE = new CarService();
    private final RentalAgreementService RENTAL_AGREEMENT_SERVICE = new RentalAgreementService();

    @GetMapping("/opret-lejekontrakt")
    public String getCreateRentalAgreement(HttpSession session, Model model) {
        // TODO: add role restriction

        model.addAttribute("cars", CAR_SERVICE.getAllCars()); // todo: make get only relevant cars
        model.addAttribute("rentalAgreement", new RentalAgreement());
        return "create-rental-agreement";
    }
}
