package com.na.bilabonnement.services;

import com.na.bilabonnement.models.User;
import com.na.bilabonnement.repositories.IUserRepository;

import java.util.List;
public class UserService
{
    final IUserRepository repo;

    /**
    *  @author Tobias Arboe
    */

    public UserService(IUserRepository repo){
        this.repo = repo;
    }

    /**
     *  @author Tobias Arboe
     */
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
