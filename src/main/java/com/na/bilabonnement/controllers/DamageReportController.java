package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.services.DamageReportService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

public class DamageReportController {
    /*
    @Author Sofia
     */
    private final DamageReportService DAMAGE_REPORT_SERVICE = new DamageReportService();

    @GetMapping("/opret-skadesrapport")
    public String getCreateDamageReport(HttpSession session, Model model){
        model.addAttribute("damageReport", new DamageReport());
        return "create-damageReport";
    }

    @PostMapping("/opret-skadesrapport")
    public String createDamageReport(HttpSession session, Model model, @ModelAttribute DamageReport damageReport){
        DamageReport createdDamageReport = DAMAGE_REPORT_SERVICE.createDamageReport(damageReport);
        return "redirect:/skadesrapport" + createdDamageReport.getId();
    }

    @GetMapping("/skadesrapporter")
    public String viewAllDamageReports(HttpSession session, Model model){
        model.addAttribute("listOfDamageReports", DAMAGE_REPORT_SERVICE.getAllDamageReports());
        return "all-damageReports";
    }


}
