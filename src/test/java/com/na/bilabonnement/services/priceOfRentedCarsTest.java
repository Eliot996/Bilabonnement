package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.DummyCarRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class priceOfRentedCarsTest
{

    @Test
    void getPriceOfRentedCars() {
        //Arrange
        CarService carService = new CarService();
        DummyCarRepo dummyCarRepo = new DummyCarRepo();

        carService.setRepo(dummyCarRepo);

        int carPriceExpected = 400;

        //Act

        int carPriceActual = carService.getPriceOfRentedCars();

        //Assert
        assertEquals(carPriceExpected, carPriceActual);
    }
}