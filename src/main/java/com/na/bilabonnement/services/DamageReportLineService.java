package com.na.bilabonnement.services;

import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.repositories.DamageReportLineRepo;
import com.na.bilabonnement.repositories.interfaces.IDamageReportLineRepository;

import java.util.List;

/*
@Author Sofia
 */
public class DamageReportLineService {
    private IDamageReportLineRepository repo = DamageReportLineRepo.getInstance();
    public void setRepo(IDamageReportLineRepository repo){
        this.repo = repo;
    }

    public DamageReportLine createDamageReportLine(DamageReportLine damageReportLine){
        return repo.create(damageReportLine);
    }

    public boolean deleteDamageReportLine(int id){
        return repo.deleteById(id);
    }

    public DamageReportLine updateDamageReportLine(int damageReportLineId, int lineNumber, String damageNotes, int price){
        DamageReportLine damageReportLine = repo.getSingleEntityById(damageReportLineId);
        damageReportLine.setDamageReportId(damageReportLineId);
        damageReportLine.setLineNumber(lineNumber);
        damageReportLine.setDamageNotes(damageNotes);
        damageReportLine.setPrice(price);

        return repo.update(damageReportLine);
    }

    public DamageReportLine getDamageReportLine(int id){
        return repo.getSingleEntityById(id);
    }

    public List<DamageReportLine> getAllDamageReportLines(){
        return repo.getAllEntities();
    }
}
