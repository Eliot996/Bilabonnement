package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.*;

public class CarService {

    private ICarRepository repo = CarRepo.getInstance();
    public void setRepo(ICarRepository repo) {
        this.repo = repo;
    }

    public Car createCar(Car car){
        return repo.create(car);
    }

    public boolean deleteCar(int carId){
        return repo.deleteById(carId);
    }
}