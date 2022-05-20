package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyDamageReportRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteDamageReportTest {
    /*
    @Author Sofia
     */
    @Test
    void deleteDamageReport(){
        //Arrange
        DamageReportService damageReportService = new DamageReportService();
        DummyDamageReportRepo dummyDamageReportRepo = new DummyDamageReportRepo();
        damageReportService.setRepo(dummyDamageReportRepo);

        final int carId0 = 0;
        final int carId1 = 1;
        final int carId2 = 2;
        final int carId3 = 3;
        final int carId4 = 4;
        final int carId5 = 5;

        boolean deleteSuccessful = true;
        boolean deleteFail = false;

        //Act
        boolean deleteCar0Fail = damageReportService.deleteDamageReport(carId0);
        boolean deleteCar1Success = damageReportService.deleteDamageReport(carId1);
        boolean deleteCar2Success = damageReportService.deleteDamageReport(carId2);
        boolean deleteCar3Success = damageReportService.deleteDamageReport(carId3);
        boolean deleteCar4Success = damageReportService.deleteDamageReport(carId4);
        boolean deleteCar5Fail =damageReportService.deleteDamageReport(carId5);

        //Assert
        assertEquals(deleteCar0Fail, deleteFail);
        assertEquals(deleteCar1Success, deleteSuccessful);
        assertEquals(deleteCar2Success, deleteSuccessful);
        assertEquals(deleteCar3Success, deleteSuccessful);
        assertEquals(deleteCar4Success, deleteSuccessful);
        assertEquals(deleteCar5Fail, deleteFail);
    }
}
