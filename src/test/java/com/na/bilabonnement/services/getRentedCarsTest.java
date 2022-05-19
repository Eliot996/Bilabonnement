package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyCarRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class getRentedCarsTest
{

    @Test
    void getRentedCars() {
        //Arrange
        CarService carService = new CarService();
        DummyCarRepo dummyCarRepo = new DummyCarRepo();

        carService.setRepo(dummyCarRepo);

        int totalNumberOfRentedCars = 3;

        //Act
        int actualNumberOfRentedCars = carService.getRentedCars().size();

        //Assert
        assertEquals(totalNumberOfRentedCars, actualNumberOfRentedCars);
    }
}