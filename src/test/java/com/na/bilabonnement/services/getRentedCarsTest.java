package com.na.bilabonnement.services;

import com.na.bilabonnement.models.CarStatus;
import com.na.bilabonnement.repositories.DummyCarRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class getRentedCarsTest
{

    @Test
    void getCarsByStatus() {
        //Arrange
        CarService carService = new CarService();
        DummyCarRepo dummyCarRepo = new DummyCarRepo();

        carService.setRepo(dummyCarRepo);

        int totalNumberOfRentedCars = 2;

        //Act
        int actualNumberOfRentedCars = carService.getCarsByStatus(CarStatus.RENTED).size();

        //Assert
        assertEquals(totalNumberOfRentedCars, actualNumberOfRentedCars);
    }
}