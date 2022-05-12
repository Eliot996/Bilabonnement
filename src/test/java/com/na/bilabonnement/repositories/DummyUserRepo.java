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
        User userOne = new User(0, "Sofia", "1234", "salt", UserRole.DATA_REGISTRATION, 0);
        User userTwo = new User(1, "Lasse", "password", "salt", UserRole.DAMAGE_AND_RECTIFICATION, 0);
        User userThree = new User(2, "Arboe", "test", "salt", UserRole.BUSINESS_DEVELOPER, 0);
        User userFour = new User(3, "Mathias", "Pass1234", "salt", UserRole.ADMINISTRATOR, 0);

        listOfUsers.add(userOne);
        listOfUsers.add(userTwo);
        listOfUsers.add(userThree);
        listOfUsers.add(userFour);
    }

    @Override
    public User create(User entity) {
        entity.setId(listOfUsers.size());
        listOfUsers.add(entity);
        return entity;
    }

    @Override
    public User getSingleEntityById(int id) {
        for (User user : listOfUsers) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllEntities() {
        return listOfUsers;
    }

    @Override
    public User update(User entity) {
        User user = getSingleEntityById(entity.getId());
        user.setUsername(entity.getUsername());
        user.setPassword(entity.getPassword());
        user.setSalt(entity.getSalt());
        user.setRole(entity.getRole());
        user.setLocationId(entity.getLocationId());
        return user;
    }

    @Override
    public boolean deleteById(int id) {
        for (User user : listOfUsers) {
            if (user.getId() == id) {
                listOfUsers.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public User getSingleEntityByUsername(String username) {
        for (User user : listOfUsers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
