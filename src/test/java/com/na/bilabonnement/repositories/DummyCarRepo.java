package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Car;

import java.util.ArrayList;
import java.util.List;

public class DummyCarRepo implements ICarRepository
{
    private List<Car> listOfCars = new ArrayList<>();

    public DummyCarRepo(){
        Car carOne = new Car(1, "1234", "home", "Fiat", "210", "to sidespejle", 100, 120, 69, 420, "none", "green", "diesel", 1);
        Car carTwo = new Car(2, "4321", "home", "Volvo", "210", "to sidespejle", 100, 120, 69, 420, "none", "red", "diesel", 1);
        Car carThree = new Car(3, "3214", "home", "Mercedes", "210", "to sidespejle", 100, 120, 69, 420, "none", "blue", "diesel", 1);
        Car carFour = new Car(4, "2341", "home", "Tesla", "210", "to sidespejle", 100, 120, 69, 420, "none", "yellow", "diesel", 1);

        listOfCars.add(carOne);
        listOfCars.add(carTwo);
        listOfCars.add(carThree);
        listOfCars.add(carFour);
    }

    @Override
    public Car create(Car entity) {
       listOfCars.add(entity);
       return entity;
    }

    @Override
    public Car getSingleEntityById(int id) {
        for (Car car: listOfCars){
            if (car.getId() == id){
                return car;
            }
        }
        return null;
    }

    @Override
    public List<Car> getAllEntities() {
        return null;
    }

    @Override
    public Car update(Car entity) {
        return null;
    }


    @Override
    public boolean deleteById(int id) {
        for (Car car: listOfCars){
            if (car.getId() == id){
                listOfCars.remove(car);
                return true;
            }
        }
        return false;
    }

    @Override
    public Car getSingleEntityByChassisNumber(String chassisNumber) {
        for (Car car: listOfCars){
            System.out.println();
            if (car.getChassisNumber().equals( chassisNumber)){
                return car;
            }
        }
        return null;
    }


}
