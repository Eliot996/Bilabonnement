package com.na.bilabonnement.services;

import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.repositories.DummyDamageReportLineRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
@Author Sofia
 */
public class CreateDamageReportLineTest {

    @Test
    void createDamageReportLine(){
        //Arrange
        DamageReportLineService damageReportLineService = new DamageReportLineService();
        damageReportLineService.setRepo(new DummyDamageReportLineRepo());
        DamageReportLine expected = new DamageReportLine(1,1,"test",1);

        //Act
        DamageReportLine actual = damageReportLineService.createDamageReportLine(expected);

        //Assert
        assertEquals(expected.getDamageReportId(),actual.getDamageReportId());
        assertEquals(expected.getLineNumber(),actual.getLineNumber());
        assertEquals(expected.getDamageNotes(),actual.getDamageNotes());
        assertEquals(expected.getPrice(),actual.getPrice());
    }
}

