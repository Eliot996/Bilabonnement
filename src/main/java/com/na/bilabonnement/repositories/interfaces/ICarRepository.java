package com.na.bilabonnement.repositories.interfaces;

import com.na.bilabonnement.models.Car;

public interface ICarRepository extends IRepository<Car> {

     Car getSingleEntityByChassisNumber(String chassisNumber);
}
