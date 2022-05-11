package com.na.bilabonnement.services;

import com.na.bilabonnement.models.Location;
import com.na.bilabonnement.repositories.IRepository;
import com.na.bilabonnement.repositories.LocationRepo;

import java.util.List;

public class LocationService {

    private final IRepository<Location> REPO = new LocationRepo();

    /**
     *  @author Mathias(Eliot996)
     */
    public List<Location> getAllLocations() {
        return REPO.getAllEntities();
    }
}
