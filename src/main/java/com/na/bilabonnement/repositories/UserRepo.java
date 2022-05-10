package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo implements IUserRepository {
    private static final UserRepo instance = new UserRepo();

    private UserRepo() {}
    public static UserRepo getInstance() {
        return instance;
    }

    @Override
    public User create(User entity) {
        Connection con = DatabaseConnectionManager.getConnection();

        String insertSQL = "INSERT INTO Users(`name`, `password`, `role`, `locationId`, `salt`) " +
                "VALUES (?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = con.prepareStatement(insertSQL);
            stmt.setString(1, entity.getUsername());
            stmt.setString(2, entity.getPassword());
            stmt.setString(3, entity.getRole().toString());
            stmt.setInt(4, entity.getLocationId());
            stmt.setString(5, entity.getSalt());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DatabaseConnectionManager.closeConnection();
        return null;
    }

    @Override
    public User getSingleEntityById(int id) {
        return null;
    }

    @Override
    public List<User> getAllEntities() {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
