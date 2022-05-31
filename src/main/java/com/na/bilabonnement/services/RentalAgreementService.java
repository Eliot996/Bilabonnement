package com.na.bilabonnement.services;

import com.na.bilabonnement.models.FileReply;
import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.repositories.interfaces.IRentalAgreementRepository;
import com.na.bilabonnement.repositories.RentalAgreementRepo;

import java.util.List;

public class RentalAgreementService {

    private static final RentalAgreementService instance = new RentalAgreementService();
    public static RentalAgreementService getInstance() {return instance;}
    private RentalAgreementService() {}

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

        bindCarsToRentalAgreement(list);

        return list;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public List<RentalAgreement> getPastEndDate() {
        List<RentalAgreement> list = repo.getAllEntitiesPastEndDate();

        bindCarsToRentalAgreement(list);

        return list;
    }

    /**
     *  @author Mathias(Eliot996)
     *  helperfunction to get the car to the right rentalagreement
     */
    private void bindCarsToRentalAgreement(List<RentalAgreement> list) {
        //goes through the Rental Agreements and find the right one based on the car's id
        //then binds the two together
        for (RentalAgreement ra : list) {
            ra.setCar(carService.getCar(ra.getCarId()));
        }
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public boolean update(RentalAgreement rentalAgreement) {
        if (rentalAgreement.getContract().isEmpty()) {
            repo.updateWithoutContract(rentalAgreement);
        } else {
            repo.update(rentalAgreement);
        }
        return true;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public RentalAgreement get(int id) {
        RentalAgreement ra = repo.getSingleEntityById(id);
        ra.setCar(carService.getCar(ra.getCarId()));
        return ra;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    public FileReply getFile(int id) {
        return repo.loadFile(id);
    }

     /**
      *  @author Arboe(H4ppyN4p)
      */
     public boolean delete(int rentalAgreementId){
         return repo.deleteById(rentalAgreementId);
     }
}
