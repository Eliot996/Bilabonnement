package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Car;

import java.util.ArrayList;
import java.util.List;

public class DummyCarRepo implements ICarRepository
{
    private List<Car> listOfCars = new ArrayList<>();

    public DummyCarRepo(){
        Car carOne = new Car(1);
        Car carTwo = new Car(2);
        Car carThree = new Car(3);
        Car carFour = new Car(4);

        listOfCars.add(carOne);
        listOfCars.add(carTwo);
        listOfCars.add(carThree);
        listOfCars.add(carFour);
    }

    @Override
    public Car create(Car entity) {
        return null;
    }

    @Override
    public Car getSingleEntityById(int id) {
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
    public Car getSingleEntityByChassisNumber(int chassisNumber) {
        return null;
    }
}
