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

public class CarRepo implements ICarRepository{

    private static final CarRepo instance = new CarRepo();
    private CarRepo(){}
    public static CarRepo getInstance(){
        return instance;
    }

    @Override
    public Car create(Car entity)
    {
        Connection conn = DatabaseConnectionManager.getConnection();

        String insertSQL = "INSERT INTO Cars (`id`, `chassisNumber`, `status`, `make`, `model`, `trimLevel`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getChassisNumber());
            stmt.setBoolean(2, entity.isStatus());
            stmt.setString(3, entity.getMake());
            stmt.setString(4, entity.getTrimLevel());
            stmt.setInt(5, entity.getScrapPrice());
            stmt.setInt(6, entity.getRegistrationFee());
            stmt.setInt(7, entity.getCo2Emission());
            stmt.setInt(8, entity.getKilometersDriven());
            stmt.setString(9, entity.getDamage());
            stmt.setString(10, entity.getColour());
            stmt.setString(11, entity.getFuelType());
            stmt.setInt(12, entity.getLocationId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return getSingleEntityByChassisNumber(entity.getChassisNumber());
    }

    private Car getSingleEntityByChassisNumber(int chassisNumber)
    {
        Connection conn = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM cars WHERE `id` = ?;";
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

    @Override
    public Car getSingleEntityByCar(String car)
    {
        return null;
    }
}
