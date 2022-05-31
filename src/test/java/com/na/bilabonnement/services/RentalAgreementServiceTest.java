package com.na.bilabonnement.services;

import com.na.bilabonnement.repositories.DummyRentalAgreementRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RentalAgreementServiceTest
{

    @Test
    void delete() {
        RentalAgreementService rentalAgreementService = RentalAgreementService.getInstance();
        DummyRentalAgreementRepo dummyRentalAgreementRepo = new DummyRentalAgreementRepo();

        rentalAgreementService.setRepo(dummyRentalAgreementRepo);
    }
}