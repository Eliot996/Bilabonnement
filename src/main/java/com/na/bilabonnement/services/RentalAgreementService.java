package com.na.bilabonnement.services;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.repositories.IRentalAgreementRepository;
import com.na.bilabonnement.repositories.RentalAgreementRepo;

import java.util.List;

public class RentalAgreementService {

    /**
     *  @author Mathias(Eliot996)
     */
    private IRentalAgreementRepository repo = RentalAgreementRepo.getInstance();
    private CarService carService =  new CarService();
    public void setRepo(IRentalAgreementRepository repo) { this.repo = repo; }
    public void setCarService(CarService carService) { this.carService = carService; }


    public RentalAgreement create(RentalAgreement rentalAgreement) {
        return repo.create(rentalAgreement);
    }

    public List<RentalAgreement> getAll() {
        List<RentalAgreement> list = repo.getAllEntities();

        for (RentalAgreement ra : list) {
            ra.setCar(carService.getCar(ra.getCarId()));
        }

        return list;
    }
}
