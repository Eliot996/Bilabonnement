package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.repositories.interfaces.IDamageReportRepository;

import java.util.ArrayList;
import java.util.List;

public class DummyDamageReportRepo implements IDamageReportRepository {
    /*
    @Author Sofia
     */
    private List<DamageReport> listOfDamageReports = new ArrayList<>();

    public DummyDamageReportRepo(){
        DamageReport damageReportOne = new DamageReport(1,"test1",1,1);
        DamageReport damageReportTwo = new DamageReport(2,"test2",2,2);
        DamageReport damageReportThree = new DamageReport(3,"test3",3,3);
        DamageReport damageReportFour = new DamageReport(4,"test4",4,4);

        listOfDamageReports.add(damageReportOne);
        listOfDamageReports.add(damageReportTwo);
        listOfDamageReports.add(damageReportThree);
        listOfDamageReports.add(damageReportFour);
    }

    @Override
    public DamageReport create(DamageReport entity) {
        listOfDamageReports.add(entity);
        return entity;
    }

    @Override
    public DamageReport getSingleEntityById(int id) {
        for (DamageReport damageReport: listOfDamageReports){
            if (damageReport.getId() == id){
                return damageReport;
            }
        }
        return null;
    }

    @Override
    public List<DamageReport> getAllEntities() {
        return listOfDamageReports;
    }

    @Override
    public DamageReport update(DamageReport entity) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        for (DamageReport damageReport: listOfDamageReports){
            if (damageReport.getId() == id){
                listOfDamageReports.remove(damageReport);
                return true;
            }
        }
        return false;
    }
}
