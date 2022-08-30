package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Car;
import com.na.bilabonnement.models.CarStatus;
import com.na.bilabonnement.repositories.interfaces.ICarRepository;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarRepo implements ICarRepository {

    private static final CarRepo instance = new CarRepo();

    private CarRepo() {}

    public static CarRepo getInstance() {
        return instance;
    }

    /**
     * @author Lasse
     * @author Tobias
     */
    @Override
    public Car create(Car entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO cars (`id`, `chassisNumber`, `status`, `make`, `model`, `trimLevel`, `carPrice`, `scrapPrice`, `registrationFee`, `co2Emission`, `kilometersDriven`, `damages`, `colour`, `fuelType`, `locationId`)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getChassisNumber());
            stmt.setString(3, entity.getStatus().toString());
            stmt.setString(4, entity.getMake());
            stmt.setString(5, entity.getModel());
            stmt.setString(6, entity.getTrimLevel());
            stmt.setInt(7, entity.getCarPrice());
            stmt.setInt(8, entity.getScrapPrice());
            stmt.setInt(9, entity.getRegistrationFee());
            stmt.setInt(10, entity.getCo2Emission());
            stmt.setInt(11, entity.getKilometersDriven());
            stmt.setString(12, entity.getDamage());
            stmt.setString(13, entity.getColour());
            stmt.setString(14, entity.getFuelType());
            stmt.setInt(15, entity.getLocationId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return getSingleEntityByChassisNumber(entity.getChassisNumber());
    }

    /**
     * @author Mathias(Eliot996)
     */
    @Override
    public Car getSingleEntityById(int id) {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM cars " +
                "WHERE `id` = ?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Car result = null;
        if (rs != null) {
            result = makeCarFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     * @author Lasse
     */
    public Car getSingleEntityByChassisNumber(String chassisNumber) {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM cars " +
                "WHERE `chassisNumber` = '" + chassisNumber + "';";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Car result = null;
        if (rs != null) {
            result = makeCarFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     * @author Mathias(Eliot996)
     */
    @Override
    public List<Car> getEntitiesByStatus(CarStatus status) {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM cars " +
                "WHERE `status` = ?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            stmt.setString(1, status.toString());
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Car> result = new ArrayList<>();
        if (rs != null) {
            result = makeCarsFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     * @author Lasse
     */
    private Car makeCarFromResultSet(ResultSet rs) {
        List<Car> cars = makeCarsFromResultSet(rs);
        if (cars.size() > 0) {
            return cars.get(0);
        }
        return null;
    }

    /**
     * @author Mathias(Eliot996)
     * @author Lasse
     */
    private List<Car> makeCarsFromResultSet(ResultSet rs) {
        ArrayList<Car> cars = new ArrayList<>();
        try {
            while (rs.next()) {
                int carId = rs.getInt("id");
                String chassisNumber = rs.getString("chassisnumber");
                CarStatus status = CarStatus.valueOf(rs.getString("status"));
                String make = rs.getString("make");
                String model = rs.getString("model");
                String trimLevel = rs.getString("trimlevel");
                int carPrice = rs.getInt("carprice");
                int scrapPrice = rs.getInt("scrapprice");
                int registrationFee = rs.getInt("registrationfee");
                int co2Emission = rs.getInt("co2emission");
                int kilometersDriven = rs.getInt("kilometersdriven");
                String damage = rs.getString("damages");
                String colour = rs.getString("colour");
                String fuelType = rs.getString("fueltype");
                int locationId = rs.getInt("locationId");
                cars.add(new Car(carId, chassisNumber, status, make, model, trimLevel,
                        carPrice, scrapPrice, registrationFee, co2Emission, kilometersDriven,
                        damage, colour, fuelType, locationId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    /**
     * @author Mathias(Eliot996)
     */
    @Override
    public List<Car> getAllEntities() {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM cars;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Car> result = new ArrayList<>();
        if (rs != null) {
            result = makeCarsFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     * @author Tobias(H4ppyN4p)
     */
    @Override
    public Car update(Car entity) {
        Connection con = DatabaseConnectionManager.getConnection();

        String insertSQL = "UPDATE `bilabonnement`.`cars`" +
                "SET `chassisNumber` = ?,  `status` = ?, `make` = ?, " +
                "`model` = ?, `trimLevel` = ?, `carPrice` = ?, `scrapPrice` = ?, " +
                "`registrationFee` = ?, `co2Emission` = ?, `kilometersDriven` = ?, " +
                "`damages` = ?, `colour` = ?, `fuelType` = ?, `locationId` = ? " +
                "WHERE (`id` = ?);";


        try {
            PreparedStatement stmt = con.prepareStatement(insertSQL);
            stmt.setString(1, entity.getChassisNumber());
            stmt.setString(2, entity.getStatus().toString());
            stmt.setString(3, entity.getMake());
            stmt.setString(4, entity.getModel());
            stmt.setString(5, entity.getTrimLevel());
            stmt.setInt(6, entity.getCarPrice());
            stmt.setInt(7, entity.getScrapPrice());
            stmt.setInt(8, entity.getRegistrationFee());
            stmt.setInt(9, entity.getCo2Emission());
            stmt.setInt(10, entity.getKilometersDriven());
            stmt.setString(11, entity.getDamage());
            stmt.setString(12, entity.getColour());
            stmt.setString(13, entity.getFuelType());
            stmt.setInt(14, entity.getLocationId());

            stmt.setInt(15, entity.getId());

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author Tobias(H4ppyN4p)
     */
    @Override
    public boolean deleteById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
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
