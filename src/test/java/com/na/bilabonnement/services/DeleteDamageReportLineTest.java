package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyDamageReportLineRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteDamageReportLineTest {

    @Test
    void deleteDamageReportLine(){
        //Arrange
        DamageReportLineService damageReportLineService = new DamageReportLineService();
        DummyDamageReportLineRepo dummyDamageReportLineRepo = new DummyDamageReportLineRepo();
        damageReportLineService.setRepo(new DummyDamageReportLineRepo());

        final int damageReportId0 = 0;
        final int damageReportId1 = 1;
        final int damageReportId2 = 2;
        final int damageReportId3 = 3;
        final int damageReportId4 = 4;
        final int damageReportId5 = 5;

        boolean deleteSuccessful = true;
        boolean deleteFail = false;

        //Act
        boolean deleteDamageReportLine0Fail = damageReportLineService.deleteDamageReportLine(damageReportId0);
        boolean deleteDamageReportLine1Success = damageReportLineService.deleteDamageReportLine(damageReportId1);
        boolean deleteDamageReportLine2Success = damageReportLineService.deleteDamageReportLine(damageReportId2);
        boolean deleteDamageReportLine3Success = damageReportLineService.deleteDamageReportLine(damageReportId3);
        boolean deleteDamageReportLine4Success = damageReportLineService.deleteDamageReportLine(damageReportId4);
        boolean deleteDamageReportLine5Fail = damageReportLineService.deleteDamageReportLine(damageReportId5);

        //Assert
        assertEquals(deleteDamageReportLine0Fail, deleteFail);
        assertEquals(deleteDamageReportLine1Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine2Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine3Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine4Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine5Fail, deleteFail);
    }
}
