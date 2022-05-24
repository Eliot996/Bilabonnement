package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.repositories.CarRepo;
import com.na.bilabonnement.services.CarService;
import com.na.bilabonnement.services.DamageReportLineService;
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
    private final DamageReportLineService DAMAGE_REPORT_LINE_SERVICE = new DamageReportLineService();

    @GetMapping("/opret-skadesrapport")
    public String getCreateDamageReport(HttpSession session, Model model){
        UserRole userRole = (UserRole)session.getAttribute("userRole");
         model.addAttribute("userRole", userRole.toString());

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
        UserRole userRole = (UserRole)session.getAttribute("userRole");
         model.addAttribute("userRole", userRole.toString());

        model.addAttribute("listOfDamageReports", DAMAGE_REPORT_SERVICE.getAllDamageReports());
        return "all-damage-reports";
    }

    @GetMapping("/skadesrapport/{damageReportId}")
    public String getEditDamageReport(HttpSession session, @PathVariable() int damageReportId, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole != UserRole.DAMAGE_AND_RECTIFICATION){
            return "redirect:/skadesrapporter";

        }

         model.addAttribute("userRole", userRole.toString());

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
    public String deleteDamageReport(HttpSession session, @PathVariable int damageReportId, Model model){
        UserRole userRole = (UserRole)session.getAttribute("userRole");
         model.addAttribute("userRole", userRole);

        DAMAGE_REPORT_SERVICE.deleteDamageReport(damageReportId);
        return "redirect:/skadesrapporter";
    }

    // for damage report lines
    @GetMapping("/opret-skade")
    public String getCreateDamageReportLine(HttpSession session, Model model){
        UserRole userRole = (UserRole)session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("damageReportLine", new DamageReportLine());
        model.addAttribute("damageReports", DAMAGE_REPORT_SERVICE.getAllDamageReports());
        return "create-damage-report-line";
    }


    @PostMapping("/opret-skade")
    public String createDamageReportLine(HttpSession session, Model model, @ModelAttribute DamageReportLine damageReportLine){
        DamageReportLine createdDamageReportLine = DAMAGE_REPORT_LINE_SERVICE.createDamageReportLine(damageReportLine);
        return "redirect:/skadesrapport/" + createdDamageReportLine.getLineNumber();
    }


    @GetMapping("/skader")
    public String viewAllDamageReportLines(HttpSession session, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("listOfDamageReportLines", DAMAGE_REPORT_LINE_SERVICE.getAllDamageReportLines());

        return "all-damage-report-lines";
    }

    @GetMapping("/skader/{damageReportId}")
    public String viewDamageReportLinesForReport(HttpSession session, @PathVariable() int damageReportId, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole != UserRole.DAMAGE_AND_RECTIFICATION){
            return "redirect:/skadesrapporter";

        }

        model.addAttribute("userRole", userRole.toString());

        DamageReport damageReport = DAMAGE_REPORT_SERVICE.getDamageReport(damageReportId);
        model.addAttribute("damageReport", damageReport);
        DamageReportLine damageReportLines = DAMAGE_REPORT_LINE_SERVICE.getDamageReportLine(damageReportId);
        model.addAttribute("damageReportLines",damageReportLines);
        return "all-damage-report-lines";
    }

    @GetMapping("/skade/{lineNumber}")
    public String getEditDamageReportLine(HttpSession session, @PathVariable() int lineNumber, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        if (userRole != UserRole.DAMAGE_AND_RECTIFICATION){
            return "redirect:/skader";

        }

        model.addAttribute("userRole", userRole.toString());

        DamageReportLine damageReportLine = DAMAGE_REPORT_LINE_SERVICE.getDamageReportLine(lineNumber);
        model.addAttribute("damageReportLine", damageReportLine);
        return "edit-damage-report-line";
    }


    @PostMapping("/skade/{lineNumber}")
    public String editDamageReportLine(HttpSession session, @ModelAttribute DamageReportLine damageReportLine, @PathVariable int lineNumber){
        DAMAGE_REPORT_LINE_SERVICE.updateDamageReportLine(damageReportLine.getLineNumber(), damageReportLine.getDamageReportId(), damageReportLine.getDamageNotes(), damageReportLine.getPrice());
        return "redirect:/skader";
    }



    @GetMapping("/skade/{lineNumber}/slet")
    public String deleteDamageReportLine(HttpSession session, @PathVariable() int lineNumber, Model model){
        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole);

        DAMAGE_REPORT_LINE_SERVICE.deleteDamageReportLine(lineNumber);
        return "redirect:/skader";
    }

}

