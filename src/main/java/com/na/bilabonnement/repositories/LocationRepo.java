package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Location;
import com.na.bilabonnement.repositories.interfaces.IRepository;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationRepo implements IRepository<Location> {

    private static LocationRepo instance = new LocationRepo();
    public static LocationRepo getInstance() {
        return instance;
    }
    private LocationRepo() {}

    @Override
    public Location create(Location entity) {
        return null;
    }

    @Override
    public Location getSingleEntityById(int id) {
        return null;
    }

    /**
      *  @author Arboe(H4ppyN4p)
      */
    @Override
    public List<Location> getAllEntities() {
        Connection con = DatabaseConnectionManager.getConnection();

        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("SELECT * FROM bilabonnement.Locations;");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayList<Location> locations = new ArrayList<>();
        try {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                locations.add(new Location(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnectionManager.closeConnection();
        return locations;
    }

    @Override
    public Location update(Location entity) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
