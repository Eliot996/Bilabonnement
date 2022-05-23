package com.na.bilabonnement.repositories.interfaces;

import com.na.bilabonnement.models.FileReply;
import com.na.bilabonnement.models.RentalAgreement;
import org.springframework.core.io.Resource;

public interface IRentalAgreementRepository extends IRepository<RentalAgreement>{
    RentalAgreement updateWithoutContract(RentalAgreement entity);

    FileReply loadFile(int id);
}
