package com.na.bilabonnement.controllers;

import com.na.bilabonnement.models.*;
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
import java.util.List;

@Controller
public class DamageReportController {
    /**
     * @author Sofia
     */
    private final CarService CAR_SERVICE = new CarService();
    private final DamageReportService DAMAGE_REPORT_SERVICE = new DamageReportService();
    private final DamageReportLineService DAMAGE_REPORT_LINE_SERVICE = new DamageReportLineService();

    /**
     *  @author Mathias(Eliot996)
     *  @author Tobias(H4ppyN4p)
     *  @author Sofia
     */
    @GetMapping("/opret-skadesrapport")
    public String getCreateDamageReport(HttpSession session, Model model) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("damageReport", new DamageReport());
        model.addAttribute("cars", CAR_SERVICE.getValidCarsForDamageReportCreation());
        return "create-damage-report";
    }

    /**
     *  @author Mathias(Eliot996)
     *  @author Sofia
     */
    @PostMapping("/opret-skadesrapport")
    public String createDamageReport(HttpSession session, @ModelAttribute DamageReport damageReport) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        damageReport.setTechnicianId((int) session.getAttribute("userID"));

        DamageReport createdDamageReport = DAMAGE_REPORT_SERVICE.createDamageReport(damageReport);
        return "redirect:/skadesrapport/" + createdDamageReport.getId();
    }

    /**
     *  @author Mathias(Eliot996)
     *  @author Tobias(H4ppyN4p)
     *  @author Sofia
     */
    @GetMapping("/skadesrapporter")
    public String viewAllDamageReports(HttpSession session, Model model) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("listOfDamageReports", DAMAGE_REPORT_SERVICE.getAllDamageReports());
        return "all-damage-reports";
    }

    /**
     * @author Mathias(Eliot996)
     * @author Sofia
     */
    @GetMapping("/skadesrapport/{damageReportId}")
    public String getEditDamageReport(HttpSession session, @PathVariable() int damageReportId, Model model) {

        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        DamageReport damageReport = DAMAGE_REPORT_SERVICE.getDamageReport(damageReportId);
        model.addAttribute("damageReport", damageReport);

        List<DamageReportLine> listOfDamageReportLines = DAMAGE_REPORT_LINE_SERVICE.
                getAllDamageReportLinesWithReportId(damageReportId);

        model.addAttribute("damageReportLines", listOfDamageReportLines);

        return "edit-damage-report";
    }

    /**
     *  @author Sofia
     *  @author Mathias(Eliot996)
     */
    @PostMapping("/skadesrapport/{damageReportId}")
    public String editDamageReport(HttpSession session, @ModelAttribute DamageReport damageReport, @PathVariable int damageReportId) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        DAMAGE_REPORT_SERVICE.updateDamageReport(damageReport.getId(), damageReport.getNotes(), damageReport.getTechnicianId(), damageReport.getCarId());
        return "redirect:/skadesrapporter";
    }

    /**
     *  @author Sofia
     *  @author Mathias(Eliot996)
     *  @author Tobias(H4ppyN4p)
     */
    @GetMapping("/skadesrapport/{damageReportId}/slet")
    public String deleteDamageReport(HttpSession session, @PathVariable int damageReportId, Model model) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole);

        DAMAGE_REPORT_SERVICE.deleteDamageReport(damageReportId);
        return "redirect:/skadesrapporter";
    }
    // for damage report lines

    /**
     * @author Lasse
     * @author Sofia
     * @author Mathias(Eliot996)
     */
    @GetMapping("/opret-skade/{damageReportId}")
    public String getCreateDamageReportLine(HttpSession session, Model model, @PathVariable int damageReportId) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        model.addAttribute("damageReportLine", new DamageReportLine());
        model.addAttribute("damageReports", DAMAGE_REPORT_SERVICE.getAllDamageReports());
        model.addAttribute("damageReportId", damageReportId);
        return "create-damage-report-line";
    }

    /**
     *  @author Lasse
     *  @author Mathias(Eliot996)
     */
    @PostMapping("/opret-skade/{damageReportId}")
    public String createDamageReportLine(HttpSession session, @ModelAttribute DamageReportLine damageReportLine) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        DamageReportLine createdDamageReportLine = DAMAGE_REPORT_LINE_SERVICE.createDamageReportLine(damageReportLine);

        return "redirect:/skadesrapport/" + createdDamageReportLine.getDamageReportId();
    }

    /**
     *  @author Mathias(Eliot996)
     *  @author Lasse
     */
    @GetMapping("/skadesrapport/{damageReportId}/{lineNumber}")
    public String getEditDamageReportLine(HttpSession session, @PathVariable() int lineNumber,
                                          @PathVariable() int damageReportId, Model model) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        UserRole userRole = (UserRole) session.getAttribute("userRole");
        model.addAttribute("userRole", userRole.toString());

        DamageReportLine damageReportLine = DAMAGE_REPORT_LINE_SERVICE.getDamageReportLine(lineNumber, damageReportId);
        model.addAttribute("damageReportLine", damageReportLine);

        return "edit-damage-report-line";
    }


    /**
     *  @author Mathias(Eliot996)
     *  @author Lasse
     */
    @PostMapping("/skadesrapport/{damageReportId}/{lineNumber}")
    public String editDamageReportLine(HttpSession session, @ModelAttribute DamageReportLine damageReportLine,
                                       @PathVariable() int damageReportId, @PathVariable int lineNumber) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        DAMAGE_REPORT_LINE_SERVICE.updateDamageReportLine(damageReportId, lineNumber, damageReportLine.getDamageNotes(), damageReportLine.getPrice());

        return "redirect:/skadesrapport/" + damageReportId;
    }


    /**
     *  @author Mathias(Eliot996)
     *  @author Lasse
     *  @author Sofia
     */
    @GetMapping("/skadesrapport/{damageReportId}/{lineNumber}/slet")
    public String deleteDamageReportLine(HttpSession session, @PathVariable() int damageReportId,
                                         @PathVariable() int lineNumber) {
        if (session.getAttribute("userRole") != UserRole.DAMAGE_AND_RECTIFICATION) {
            return "redirect:/logout";
        }

        DAMAGE_REPORT_LINE_SERVICE.deleteDamageReportLine(lineNumber, damageReportId);
        return "redirect:/skadesrapport/" + damageReportId;
    }

}

