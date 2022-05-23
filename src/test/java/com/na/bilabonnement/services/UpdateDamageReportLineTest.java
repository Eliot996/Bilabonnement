package com.na.bilabonnement.services;

import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.repositories.DummyDamageReportLineRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
@Author Sofia
 */
public class UpdateDamageReportLineTest {
    @Test
    void updateDamageReportLineTest(){
        //Arrange
        DamageReportLineService damageReportLineService = new DamageReportLineService();
        DummyDamageReportLineRepo dummyDamageReportLineRepo = new DummyDamageReportLineRepo();
        damageReportLineService.setRepo(dummyDamageReportLineRepo);

        int expectedDamageReportId = 1;
        String expectedDamageNotes = "Test bemærkninger";
        int expectedPrice = 1;

        //Act
        damageReportLineService.updateDamageReportLine(1,1,"Test bemærkninger",1); //setting my linenumber to 1
        DamageReportLine damageReportLine = dummyDamageReportLineRepo.getSingleEntityById(1); //i choose 1 because its my linenumber

        //Assert
        assertEquals(damageReportLine.getDamageReportId(),expectedDamageReportId);
        assertEquals(damageReportLine.getDamageNotes(),expectedDamageNotes);
        assertEquals(damageReportLine.getPrice(),expectedPrice);
    }
}
