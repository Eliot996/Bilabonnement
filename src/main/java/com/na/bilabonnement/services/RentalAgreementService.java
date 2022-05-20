package com.na.bilabonnement.services;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.repositories.IRentalAgreementRepository;
import com.na.bilabonnement.repositories.RentalAgreementRepo;

import java.util.List;

public class RentalAgreementService {

    private IRentalAgreementRepository repo = RentalAgreementRepo.getInstance();
    private CarService carService =  new CarService();
    public void setRepo(IRentalAgreementRepository repo) { this.repo = repo; }
    public void setCarService(CarService carService) { this.carService = carService; }


    /**
     *  @author Mathias(Eliot996)
     */
    public RentalAgreement create(RentalAgreement rentalAgreement) {
        return repo.create(rentalAgreement);
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public List<RentalAgreement> getAll() {
        List<RentalAgreement> list = repo.getAllEntities();

        for (RentalAgreement ra : list) {
            ra.setCar(carService.getCar(ra.getCarId()));
        }

        return list;
    }
}
