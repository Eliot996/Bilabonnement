package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo implements IUserRepository {
    private static final UserRepo instance = new UserRepo();

    private UserRepo() {}
    public static UserRepo getInstance() {
        return instance;
    }

    /**
     *  @author Mathias(Eliot996)
     *  Will push the user to the database, and fetch the created user and return it (to get the correct id)
     */
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

        return getSingleEntityByUsername(entity.getUsername());
    }

    @Override
    public User getSingleEntityById(int id) {
        return null;
    }


    /**
     *  @author Mathias(Eliot996)
     *  @author Arboe(H4ppyN4p)
     */
    @Override
    public User getSingleEntityByUsername(String username) {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM users " +
                "WHERE `name` = '" + username +  "';";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        User result = null;
        if (rs != null) {
            result = makeUserFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Override
    public List<User> getAllEntities() {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM users;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> result = new ArrayList<>();
        if (rs != null) {
            result =  makeUsersFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    /**
     *  @author Mathias(Eliot996)
     *  Make a user from the resultset
     */
    private User makeUserFromResultSet(ResultSet rs) {
        List<User> users = makeUsersFromResultSet(rs);
        if (users.size() > 0) {
            return users.get(0);
        }
        return null;
    }

    /**
     *  @author Mathias(Eliot996)
     *  make a list of users from the given resultset
     */
    private List<User> makeUsersFromResultSet(ResultSet rs) {
        ArrayList<User> users = new ArrayList<>();
        try {
            while(rs.next()){
                int userId = rs.getInt("id");
                String userName = rs.getString("name");
                String userPassword = rs.getString("password");
                String userSalt = rs.getString("salt");
                UserRole userRole = UserRole.valueOf(rs.getString("role"));
                int locationID = rs.getInt("locationId");
                users.add(new User(userId, userName, userPassword, userSalt, userRole, locationID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
