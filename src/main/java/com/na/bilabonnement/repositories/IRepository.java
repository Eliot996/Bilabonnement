package com.na.bilabonnement.repositories;

import java.util.List;

public interface IRepository<T>{
    //Create
    public T create(T entity);

    //Read
    public T getSingleEntityById(int id);


    public List<T> getAllEntities();

    //Update
    public T update(T entity);

    //Delete
    public boolean deleteById(int id);
}
