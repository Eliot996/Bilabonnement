package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.models.UserRole;

import java.util.ArrayList;
import java.util.List;

public class DummyUserRepo implements IUserRepository

//Made by: Tobias Arboe

{
    private List<User> listOfUsers = new ArrayList<User>();

    public DummyUserRepo(){
        User userOne = new User(-1, "Sofia", "1234", "salt", UserRole.DATA_REGISTRATION, 0);
        User userTwo = new User(-1, "Lasse", "password", "salt", UserRole.DAMAGE_AND_RECTIFICATION, 0);
        User userThree = new User(-1, "Arboe", "test", "salt", UserRole.BUSINESS_DEVELOPER, 0);
        User userFour = new User(-1, "Mathias", "Pass1234", "salt", UserRole.ADMINISTRATOR, 0);

        listOfUsers.add(userOne);
        listOfUsers.add(userTwo);
        listOfUsers.add(userThree);
        listOfUsers.add(userFour);
    }

    @Override
    public User create(User entity) {


        return entity;
    }

    @Override
    public User getSingleEntityById(int id) {
        return null;
    }

    @Override
    public List<User> getAllEntities() {
        return listOfUsers;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public User getSingleEntityByUsername(String username) {
        return null;
    }
}
