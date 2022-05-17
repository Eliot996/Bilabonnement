package com.na.bilabonnement.repositories;
import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo implements ICarRepository{

    private static final CarRepo instance = new CarRepo();

    private CarRepo() {}
    public static CarRepo getInstance() {
        return instance;
    }

    /*
    @Author Lasse
    */
    @Override
    public Car create(Car entity)
    {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO Cars (`id`, `chassisNumber`, `status`, `make`, `model`, `trimLevel`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getChassisNumber());
            stmt.setString(2, entity.getStatus());
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


    @Override
    public Car getSingleEntityById(int id)
    {
        return null;
    }

    /*
    @Author Lasse
    */

    @Override
    public Car getSingleEntityByChassisNumber(int chassisNumber)
    {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM cars " +
                "WHERE `chassisNumber` = '" + chassisNumber +  "';";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        }   catch (SQLException e) {
            e.printStackTrace();
        }

        Car result = null;
        if (rs != null){
            result = makeCarFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /*
    @Author Lasse
    */

    private Car makeCarFromResultSet(ResultSet rs)
    {
        List<Car> cars = makeCarsFromResultSet(rs);
        if (cars.size() > 0) {
            return cars.get(0);
        }
        return null;
    }

    private List<Car> makeCarsFromResultSet(ResultSet rs) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            while(rs.next()) {
                int carId = rs.getInt("id");
                int chassisNumber = rs.getInt("chassisnumber");
                String status = rs.getString("status");
                String make = rs.getString("make");
                String trimLevel = rs.getString("trimlevel");
                int scrapPrice = rs.getInt("scrapprice");
                int registrationFee = rs.getInt("registrationfee");
                int co2Emission = rs.getInt("co2emission");
                int kilometersDriven = rs.getInt("kilometersdriven");
                String damage = rs.getString("damage");
                String colour = rs.getString("colour");
                String fuelType = rs.getString("fueltype");
                int locationId = rs.getInt("locationId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            }
        return cars;
        }

    @Override
    public List<Car> getAllEntities()
    {
        return null;
    }

    @Override
    public Car update(Car entity)
    {
        Connection con = DatabaseConnectionManager.getConnection();

        String insertSQL = "UPDATE `bilabonnement`.`car`" +
                            "SET `id` = ?,  `status` = ?, `make` = ?, `model` = ?, `trimLevel` = ?, `scrapPrice` = ?, `registrationFee` = ?, `co2Emission` = ?, `kilometersDriven` = ?, `damages` = ?, `colour` = ?, `fuelType` = ?, `locationId` = ?" +
                            "WHERE (`id` = ?);";



        try {
            PreparedStatement stmt = con.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getStatus());
            stmt.setString(3, entity.getMake());
            stmt.setString(4, entity.getModel());
            stmt.setString(5, entity.getTrimLevel());
            stmt.setInt(6, entity.getScrapPrice());
            stmt.setInt(7, entity.getRegistrationFee());
            stmt.setInt(8, entity.getCo2Emission());
            stmt.setInt(9, entity.getKilometersDriven());
            stmt.setString(10, entity.getDamage());
            stmt.setString(11, entity.getColour());
            stmt.setString(12, entity.getFuelType());
            stmt.setInt(11, entity.getLocationId());


            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try{
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM cars WHERE id=?");
            stmt.setInt(1, id);
            stmt.execute();
            DatabaseConnectionManager.closeConnection();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

}
