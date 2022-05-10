package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.Location;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationRepo implements IRepository<Location>{
    @Override
    public Location create(Location entity) {
        return null;
    }

    @Override
    public Location getSingleEntityById(int id) {
        return null;
    }

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
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return locations;
    }

    @Override
    public boolean update(Location entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
