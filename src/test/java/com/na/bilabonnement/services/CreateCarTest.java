package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.DummyCarRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCarTest {

    @Test
    void createCar(){
        //Arrange
        CarService carService = new CarService();
        carService.setRepo(new DummyCarRepo());
        Car expected = new Car(5,123456,"status","make","Model","trimlevel",10000,20000,30000,100000,"damage","colour","fueltype",1);

        //Act
        Car actual = carService.createCar(expected);

        //Assert
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getChassisNumber(), actual.getChassisNumber());
        assertEquals(expected.getStatus(),actual.getStatus());
        assertEquals(expected.getMake(),actual.getMake());
        assertEquals(expected.getModel(),actual.getModel());
        assertEquals(expected.getTrimLevel(),actual.getTrimLevel());
        assertEquals(expected.getScrapPrice(),actual.getScrapPrice());
        assertEquals(expected.getRegistrationFee(),actual.getRegistrationFee());
        assertEquals(expected.getCo2Emission(),actual.getCo2Emission());
        assertEquals(expected.getKilometersDriven(),actual.getKilometersDriven());
        assertEquals(expected.getDamage(),actual.getDamage());
        assertEquals(expected.getColour(),actual.getColour());
        assertEquals(expected.getFuelType(),actual.getFuelType());
        assertEquals(expected.getLocationId(),actual.getLocationId());
    }
}
