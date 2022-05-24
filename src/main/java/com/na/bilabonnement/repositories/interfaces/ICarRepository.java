package com.na.bilabonnement.repositories.interfaces;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;

import java.util.List;

public interface ICarRepository extends IRepository<Car> {

     Car getSingleEntityByChassisNumber(String chassisNumber);
     List<Car> getEntitiesByStatus(CarStatus status);
}
