package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;
import com.na.bilabonnement.repositories.*;
import com.na.bilabonnement.repositories.interfaces.ICarRepository;

import java.util.ArrayList;
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

     /**
          *  @author Arboe(H4ppyN4p)
          */
    public Car updateCar(int id, String chassisNumber, CarStatus status, String make, String model, String trimLevel,
                         int carPrice, int scrapPrice, int registrationFee, int co2Emission, int kilometersDriven,
                         String damage, String colour, String fuelType, int locationId) {

        Car car = repo.getSingleEntityById(id);


        car.setChassisNumber(chassisNumber);
        car.setStatus(status);
        car.setMake(make);
        car.setModel(model);
        car.setTrimLevel(trimLevel);
        car.setCarPrice(carPrice);
        car.setScrapPrice(scrapPrice);
        car.setRegistrationFee(registrationFee);
        car.setCo2Emission(co2Emission);
        car.setKilometersDriven(kilometersDriven);
        car.setDamage(damage);
        car.setColour(colour);
        car.setFuelType(fuelType);
        car.setLocationId(locationId);

        return repo.update(car);
    }

    public Car getCar(int id){
        return repo.getSingleEntityById(id);
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public List<Car> getAllCars() {
        return repo.getAllEntities();
    }


     /**
          *  @author Arboe(H4ppyN4p)
          */
    public List<Car> getRentedCars(){
        ArrayList<Car> listOfRentedCars = new ArrayList<>();

        for (Car car: repo.getAllEntities()
        ) {
            if (car.getStatus() == CarStatus.RENTED)
            {
                listOfRentedCars.add(car);
            }
        }
        return listOfRentedCars;
    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    public int getPriceOfRentedCars(){
        int totalPrice = 0;

        for (Car car: getRentedCars()
             ) {
            totalPrice += car.getCarPrice();
        }

        return totalPrice;
    }
}