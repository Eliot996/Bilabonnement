package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;
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

        int expectedId = 3;
        String expectedTrimLevel = "trimlevel";
        int expectedScrapPrice = 10000;
        String expectedChassisNumber = "5678";

        //Act
        carService.updateCar(3, "5678", CarStatus.READY_FOR_DELIVERY,"make","Model","trimlevel", 1000, 10000,20000,30000,100000,"damage","colour","fueltype",3);
        Car car = dummyCarRepo.getSingleEntityById(3);

        //Assert
        assertEquals(car.getId(), expectedId);
        assertEquals(car.getChassisNumber(), expectedChassisNumber);
        assertEquals(car.getTrimLevel(), expectedTrimLevel);
        assertEquals(car.getScrapPrice(), expectedScrapPrice);
    }
}