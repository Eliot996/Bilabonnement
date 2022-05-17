package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyCarRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCarTest
{

    @Test
    void deleteCar() {
        //Arrange
        CarService carService = new CarService();
        DummyCarRepo dummyCarRepo = new DummyCarRepo();

        carService.setRepo(dummyCarRepo);

        final int carId0 = 0;
        final int carId1 = 1;
        final int carId2 = 2;
        final int carId3 = 3;
        final int carId4 = 4;
        final int carId5 = 5;

        boolean successfulDelete = true;
        boolean failDelete = false;

        //Act
        boolean deleteCar0Fail = dummyCarRepo.deleteById(carId0);
        boolean deleteCar1Success = dummyCarRepo.deleteById(carId1);
        boolean deleteCar2Success = dummyCarRepo.deleteById(carId2);
        boolean deleteCar3Success = dummyCarRepo.deleteById(carId3);
        boolean deleteCar4Success = dummyCarRepo.deleteById(carId4);
        boolean deleteCar5Fail = dummyCarRepo.deleteById(carId5);

        //Assert
        assertEquals(deleteCar0Fail, failDelete);
        assertEquals(deleteCar5Fail, failDelete);
        assertEquals(deleteCar1Success, successfulDelete);
        assertEquals(deleteCar2Success, successfulDelete);
        assertEquals(deleteCar3Success, successfulDelete);
        assertEquals(deleteCar4Success, successfulDelete);
    }
}