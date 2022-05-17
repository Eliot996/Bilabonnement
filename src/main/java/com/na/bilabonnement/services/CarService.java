package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.CarRepo;
import com.na.bilabonnement.repositories.ICarRepository;
import com.na.bilabonnement.repositories.IUserRepository;

public class CarService {
    private ICarRepository repo = (ICarRepository) CarRepo.getInstance();
    public void setRepo(ICarRepository repo) {
        this.repo = repo;
    }

    public Car createCar(int id, int chassisNumber, boolean status, String make, String trimLevel,
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
}