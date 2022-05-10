package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Location;
import com.na.bilabonnement.repositories.IRepository;
import com.na.bilabonnement.repositories.LocationRepo;

import java.util.List;

public class LocationService {

    IRepository<Location> repo = new LocationRepo();

    public List<Location> getAllLocations() {
        return repo.getAllEntities();
    }
}
