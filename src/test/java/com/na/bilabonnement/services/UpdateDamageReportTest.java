package com.na.bilabonnement.services;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.repositories.DummyDamageReportRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateDamageReportTest {

    @Test
    void updateDamageReportTest(){
        //Arrange
        DamageReportService damageReportService = new DamageReportService();
        DummyDamageReportRepo dummyDamageReportRepo = new DummyDamageReportRepo();
        damageReportService.setRepo(dummyDamageReportRepo);


        String expectedNotes = "Test bemærkninger";
        int expectedTechnicianId = 3;
        int expectedCarId = 3;

        //Act
        damageReportService.updateDamageReport(3,"Test bemærkninger",3,3); //setting my id to 3.
        DamageReport damageReport = dummyDamageReportRepo.getSingleEntityById(3); //i choose 3 because thats my id number from the update method

        //Assert
        assertEquals(damageReport.getNotes(),expectedNotes);
        assertEquals(damageReport.getTechnicianId(),expectedTechnicianId);
        assertEquals(damageReport.getCarId(),expectedCarId);
    }
}
