package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.User;

import java.util.List;

public class UserRepo implements IUserRepository {
    @Override
    public User create(User entity) {
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
