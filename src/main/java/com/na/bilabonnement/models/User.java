package com.na.bilabonnement.models;


/**
 *  @author Tobias Arboe
 *  @author Mathias(Eliot996)
*/

public class User {
    private String username;
    private String password;
    private String salt;
    private UserRole role;
    private int roleID;
    private int locationId;

    public User(String username, String password, String salt, UserRole role, int locationId) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.role = role;
        this.locationId = locationId;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
}
