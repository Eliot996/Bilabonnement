package com.na.bilabonnement.services;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.repositories.DamageReportRepo;
import com.na.bilabonnement.repositories.UserRepo;
import com.na.bilabonnement.repositories.interfaces.IRepository;
import com.na.bilabonnement.repositories.interfaces.IUserRepository;

import java.util.List;

/*
@Author Sofia
 */
public class DamageReportService {
    private IRepository<DamageReport> repo = DamageReportRepo.getInstance();
    private IUserRepository userRepo = UserRepo.getInstance();
    public void setRepo(IRepository<DamageReport> repo) {
        this.repo = repo;
    }

    public DamageReport createDamageReport(DamageReport damageReport){
        return repo.create(damageReport);
    }

    public boolean deleteDamageReport(int id){
        return repo.deleteById(id);
    }

    public DamageReport updateDamageReport(int id, String notes, int technicianId, int carId){
        DamageReport damageReport = repo.getSingleEntityById(id);

        damageReport.setCarId(carId);
        damageReport.setTechnicianId(technicianId);
        damageReport.setNotes(notes);

        return repo.update(damageReport);
    }

    public DamageReport getDamageReport(int id){
        return repo.getSingleEntityById(id);
    }

    public List<DamageReport> getAllDamageReports(){
        List<DamageReport> list = repo.getAllEntities();

        for (DamageReport dr : list) {
            dr.setTechnicianName(
                    userRepo.
                    getSingleEntityById(dr.getTechnicianId()).
                    getUsername());
        }

        return list;
    }
}
