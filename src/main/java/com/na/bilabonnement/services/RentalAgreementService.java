package com.na.bilabonnement.services;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.repositories.IRentalAgreementRepository;
import com.na.bilabonnement.repositories.RentalAgreementRepo;

public class RentalAgreementService {

    /**
     *  @author Mathias(Eliot996)
     */
    private IRentalAgreementRepository repo = new RentalAgreementRepo();
    public void setRepo(IRentalAgreementRepository repo) { this.repo = repo; }


    public RentalAgreement create(RentalAgreement rentalAgreement) {
        return repo.create(rentalAgreement);
    }
}
