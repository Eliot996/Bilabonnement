package com.na.bilabonnement.repositories;
import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo implements IRepository <Car> {

    private static final CarRepo instance = new CarRepo();
    private CarRepo(){}
    public static CarRepo getInstance(){
        return instance;
    }

    @Override
    public Car create(Car entity)
    {
        Connection conn = DatabaseConnectionManager.getConnection();

        return null;
    }

    @Override
    public Car getSingleEntityById(int id)
    {
        return null;
    }

    @Override
    public List<Car> getAllEntities()
    {
        return null;
    }

    @Override
    public Car update(Car entity)
    {
        return null;
    }

    @Override
    public boolean deleteById(int id)
    {
        return false;
    }

}
