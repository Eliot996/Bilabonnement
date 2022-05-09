package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.repositories.IUserRepository;

import java.util.List;

//Made by: Tobias Arboe
public class UserService
{
    final IUserRepository repo;

    public UserService(IUserRepository repo){
        this.repo = repo;
    }

    public boolean checkLogin(String expectedUsername, String expectedPassword){
        boolean loginValidity = false;

        List<User> listOfUsers = repo.getAllEntities();

        for (User user: listOfUsers
             ) {
            if (user.getUsername().equals(expectedUsername) && user.getPassword().equals(expectedPassword) ){
                loginValidity = true;
            }
        }

        return loginValidity;
    }
}
