package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.*;

public class CarService {

    private ICarRepository repo = CarRepo.getInstance();
    public void setRepo(ICarRepository repo) {
        this.repo = repo;
    }

    public Car createCar(int id, int chassisNumber, String status, String make, String trimLevel,
                         int scrapPrice, int registrationFee, int co2Emission, int kilometersDriven,
                         String damage, String colour, String fuelType, int locationId){
        Car car = new Car(
                id,
                chassisNumber,
                status,
                make,
                trimLevel,
                scrapPrice,
                registrationFee,
                co2Emission,
                kilometersDriven,
                damage,
                colour,
                fuelType,
                locationId);
        return repo.create(car);
    }

    public boolean deleteCar(int carId){
        return repo.deleteById(carId);
    }
}