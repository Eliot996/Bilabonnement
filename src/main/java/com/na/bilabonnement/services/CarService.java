package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;
import com.na.bilabonnement.repositories.*;
import com.na.bilabonnement.repositories.interfaces.ICarRepository;

import java.util.List;

public class CarService {
    private ICarRepository repo = CarRepo.getInstance();

    public void setRepo(ICarRepository repo) {
        this.repo = repo;
    }

    /**
     *  @author Sofia
     */
    public Car createCar(Car car){
        car.setStatus(CarStatus.READY_TO_BE_RENTED);
        return repo.create(car);
    }

    /**
     *  @author Arboe(H4ppyN4p)
     */
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

    /**
     *  @author Arboe(H4ppyN4p)
     */
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
    public int getPriceOfRentedCars(){
        int totalPrice = 0;

        for (Car car: getCarsByStatus(CarStatus.RENTED)
             ) {
            totalPrice += car.getCarPrice();
        }

        return totalPrice;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public List<Car> getCarsByStatus(CarStatus status) {
        return repo.getEntitiesByStatus(status);
    }

    /**
     *  @author Mathias(Eliot996)
     *  Added to help set the right value to match the status
     */
    public int getCarStatusValue(Car car) {
        switch (car.getStatus()) {
            case READY_TO_BE_RENTED:     return 1;
            case READY_FOR_DELIVERY:     return 2;
            case RENTED:                 return 3;
            case BACK_FROM_BEING_RENTED: return 4;
            case READY_FOR_SALE:         return 5;
        }
        return -1;
    }
}