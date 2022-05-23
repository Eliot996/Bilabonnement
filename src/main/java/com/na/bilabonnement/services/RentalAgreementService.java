package com.na.bilabonnement.services;

import com.na.bilabonnement.models.FileReply;
import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.repositories.interfaces.IRentalAgreementRepository;
import com.na.bilabonnement.repositories.RentalAgreementRepo;
import org.springframework.core.io.Resource;

import java.io.File;
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

    /**
     *  @author Mathias(Eliot996)
     */
    public boolean update(RentalAgreement rentalAgreement) {
        if (rentalAgreement.getContract().isEmpty()) {
            repo.updateWithoutContract(rentalAgreement);
        }else {
            repo.update(rentalAgreement);
        }
        return true;
    }

    public RentalAgreement get(int id) {
        RentalAgreement ra = repo.getSingleEntityById(id);
        ra.setCar(carService.getCar(ra.getCarId()));
        return ra;
    }

    public FileReply getFile(int id) {
        return repo.loadFile(id);
    }
}
