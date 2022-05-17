package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.User;

public interface ICarRepository extends IRepository<Car>{

    public Car getSingleEntityByCar(String car);
}




