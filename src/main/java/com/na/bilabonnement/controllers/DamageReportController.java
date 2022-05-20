package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.repositories.CarRepo;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.DamageReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
@Controller
public class DamageReportController {
    /*
    @Author Sofia
     */
    private final DamageReportService DAMAGE_REPORT_SERVICE = new DamageReportService();
    private final CarService CAR_SERVICE = new CarService();

    @GetMapping("/opret-skadesrapport")
    public String getCreateDamageReport(HttpSession session, Model model){
        model.addAttribute("damageReport", new DamageReport());
        model.addAttribute("cars", CAR_SERVICE.getAllCars()); // todo: make get only relevant cars
        return "create-damage-report";
    }

    @PostMapping("/opret-skadesrapport")
    public String createDamageReport(HttpSession session, Model model, @ModelAttribute DamageReport damageReport){
        DamageReport createdDamageReport = DAMAGE_REPORT_SERVICE.createDamageReport(damageReport);
        return "redirect:/skadesrapport/" + createdDamageReport.getId();
    }

    @GetMapping("/skadesrapporter")
    public String viewAllDamageReports(HttpSession session, Model model){
        model.addAttribute("listOfDamageReports", DAMAGE_REPORT_SERVICE.getAllDamageReports());
        return "all-damage-reports";
    }

    @GetMapping("/skadesrapport/{damageReportId}")
    public String getEditDamageReport(HttpSession session, @PathVariable() int damageReportId, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole != UserRole.DAMAGE_AND_RECTIFICATION){
            return "redirect:/skadesrapporter";
        }
        DamageReport damageReport = DAMAGE_REPORT_SERVICE.getDamageReport(damageReportId);
        model.addAttribute("damageReport", damageReport);
        return "edit-damage-report";
    }

    @PostMapping("/skadesrapport/{damageReportId}")
    public String editDamageReport(HttpSession session, @ModelAttribute DamageReport damageReport, @PathVariable int damageReportId){
        DAMAGE_REPORT_SERVICE.updateDamageReport(damageReport.getId(),damageReport.getNotes(),damageReport.getTechnicianId(), damageReport.getCarId());
        return "redirect:/skadesrapporter";
    }

    @GetMapping("/skadesrapport/{damageReportId}/slet")
    public String deleteDamageReport(HttpSession session, @PathVariable int damageReportId){
        DAMAGE_REPORT_SERVICE.deleteDamageReport(damageReportId);
        return "redirect:/skadesrapporter";
    }
}

