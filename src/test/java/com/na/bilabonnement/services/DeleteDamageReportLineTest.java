package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyDamageReportLineRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteDamageReportLineTest {

    /*
    @Author Sofia
     */
    @Test
    void deleteDamageReportLine(){
        //Arrange
        DamageReportLineService damageReportLineService = new DamageReportLineService();
        damageReportLineService.setRepo(new DummyDamageReportLineRepo());

        final int damageReportId0 = 0;
        final int lineNumber0 = 0;
        final int damageReportId1 = 1;
        final int lineNumber1 = 1;
        final int damageReportId2 = 2;
        final int lineNumber2 = 2;
        final int damageReportId3 = 3;
        final int lineNumber3 = 3;
        final int damageReportId4 = 4;
        final int lineNumber4 = 4;
        final int damageReportId5 = 5;
        final int lineNumber5 = 5;

        boolean deleteSuccessful = true;
        boolean deleteFail = false;

        //Act
        boolean deleteDamageReportLine0Fail = damageReportLineService.deleteDamageReportLine(lineNumber0,damageReportId0);
        boolean deleteDamageReportLine1Success = damageReportLineService.deleteDamageReportLine(lineNumber1, damageReportId1);
        boolean deleteDamageReportLine2Success = damageReportLineService.deleteDamageReportLine(lineNumber2, damageReportId2);
        boolean deleteDamageReportLine3Success = damageReportLineService.deleteDamageReportLine(lineNumber3, damageReportId3);
        boolean deleteDamageReportLine4Success = damageReportLineService.deleteDamageReportLine(lineNumber4, damageReportId4);
        boolean deleteDamageReportLine5Fail = damageReportLineService.deleteDamageReportLine(lineNumber5, damageReportId5);

        //Assert
        assertEquals(deleteDamageReportLine0Fail, deleteFail);
        assertEquals(deleteDamageReportLine1Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine2Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine3Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine4Success, deleteSuccessful);
        assertEquals(deleteDamageReportLine5Fail, deleteFail);
    }
}
