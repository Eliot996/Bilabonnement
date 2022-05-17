package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.repositories.*;

import java.util.List;

public class CarService {
/*
@Author Sofia
 */
    private ICarRepository repo = CarRepo.getInstance();
    public void setRepo(ICarRepository repo) {
        this.repo = repo;
    }
/*
@Author Sofia
 */
    public Car createCar(Car car){
        return repo.create(car);
    }

    public boolean deleteCar(int carId){
        return repo.deleteById(carId);
    }

    public List<Car> getAllCars() {
        return repo.getAllEntities();
    }
}