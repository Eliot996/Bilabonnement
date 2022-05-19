package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.IRentalAgreementRepository;
import com.na.bilabonnement.repositories.RentalAgreementRepo;

public class RentalAgreementService {

    private IRentalAgreementRepository repo = new RentalAgreementRepo();
    public void setRepo(IRentalAgreementRepository repo) { this.repo = repo; }


}
