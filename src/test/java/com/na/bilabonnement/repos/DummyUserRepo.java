package com.na.bilabonnement.repos;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

public class DummyUserRepo implements IUserRepository

//Made by: Tobias Arboe

{
    private List<User> listOfUsers = new ArrayList<User>();

    public DummyUserRepo(){
        User userOne = new User("Sofia", "1234");
        User userTwo = new User("Lasse", "password");
        User userThree = new User("Arboe", "test");
        User userFour = new User("Mathias", "Pass1234");

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
}
