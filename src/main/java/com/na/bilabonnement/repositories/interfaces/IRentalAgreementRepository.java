package com.na.bilabonnement.repositories.interfaces;

import com.na.bilabonnement.models.FileReply;
import com.na.bilabonnement.models.RentalAgreement;

public interface IRentalAgreementRepository extends IRepository<RentalAgreement>{
    RentalAgreement updateWithoutContract(RentalAgreement entity);

    FileReply loadFile(int id);
}
