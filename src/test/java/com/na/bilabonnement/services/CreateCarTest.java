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
        Car expected = new Car(5,123456,"status","make","trimlevel",10000,20000,30000,100000,"damage","colour","fueltype",1);

        //Act
        Car actual = carService.createCar(expected);

        //Assert
        assertEquals(expected.getChassisNumber(), actual.getChassisNumber());
        assertEquals(expected.getId(),actual.getId());
        assertEquals(expected.getColour(),actual.getColour());
        assertEquals(expected.getKilometersDriven(),actual.getKilometersDriven());
    }
}
