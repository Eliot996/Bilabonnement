package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.User;

public interface IUserRepository extends IRepository<User>{

    public User getSingleEntityByUsername(String username);

}
