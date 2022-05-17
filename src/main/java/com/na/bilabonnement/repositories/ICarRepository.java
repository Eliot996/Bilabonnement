package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Car;

public interface ICarRepository extends IRepository<Car> {

    public Car getSingleEntityByChassisNumber(int chassisNumber);

}
