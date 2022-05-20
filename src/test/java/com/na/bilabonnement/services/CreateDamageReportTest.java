package com.na.bilabonnement.services;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.repositories.DummyDamageReportRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateDamageReportTest {
    /*
    @Author Sofia
     */
    @Test
    void createDamageReport(){
        // Arrange
        DamageReportService damageReportService = new DamageReportService();
        damageReportService.setRepo(new DummyDamageReportRepo());
        DamageReport expected = new DamageReport(1,"ingen bemærkning",1,1);

        // Act
        DamageReport actual = damageReportService.createDamageReport(expected);

        // Assert
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getCarId(),actual.getCarId());
        assertEquals(expected.getNotes(),actual.getNotes());
        assertEquals(expected.getTechnicianId(),actual.getTechnicianId());

    }
}
