package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.DummyCarRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCarTest
{

    @Test
    void updateCar() {
        //Arrange
        CarService carService = new CarService();
        DummyCarRepo dummyCarRepo = new DummyCarRepo();

        carService.setRepo(dummyCarRepo);

        int expectedId = 5;
        String expectedTrimLevel = "trimlevel";
        int expectedScrapPrice = 10000;

        //Act
        carService.updateCar(5, "1234", "status","make","Model","trimlevel", 1000, 10000,20000,30000,100000,"damage","colour","fueltype",1);
        Car car = dummyCarRepo.getSingleEntityByChassisNumber("1234");

        //Assert
        assertEquals(car.getId(), expectedId);
        assertEquals(car.getTrimLevel(), expectedTrimLevel);
        assertEquals(car.getScrapPrice(), expectedScrapPrice);
    }
}