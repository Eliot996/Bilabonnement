package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.repositories.interfaces.IDamageReportLineRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
@Author Sofia
 */
public class DummyDamageReportLineRepo implements IDamageReportLineRepository
{
    private List<DamageReportLine> listOfDamageReportLines = new ArrayList<>();

    public DummyDamageReportLineRepo(){
        DamageReportLine damageReportLineOne = new DamageReportLine(1,1,"test1",1);
        DamageReportLine damageReportLineTwo = new DamageReportLine(2,2,"test2",2);
        DamageReportLine damageReportLineThree = new DamageReportLine(3,3,"test3",3);
        DamageReportLine damageReportLineFour = new DamageReportLine(4,4,"test4",4);

        listOfDamageReportLines.add(damageReportLineOne);
        listOfDamageReportLines.add(damageReportLineTwo);
        listOfDamageReportLines.add(damageReportLineThree);
        listOfDamageReportLines.add(damageReportLineFour);
    }


    @Override
    public DamageReportLine create(DamageReportLine entity) {
        listOfDamageReportLines.add(entity);
        return entity;
    }

    @Override
    public DamageReportLine getSingleEntityById(int id) {
        for (DamageReportLine damageReportLine: listOfDamageReportLines){
            if (damageReportLine.getLineNumber() == id){
                return damageReportLine;
            }
        }
        return null;
    }

    @Override
    public List<DamageReportLine> getAllEntities() {
        return listOfDamageReportLines;
    }

    @Override
    public DamageReportLine update(DamageReportLine entity) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        for (DamageReportLine damageReportLine: listOfDamageReportLines){
            if (damageReportLine.getLineNumber() == id){
                listOfDamageReportLines.remove(damageReportLine);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<DamageReportLine> getAllEntitiesWithDamageReportId(int damageReportId) {
        return null;
    }
}
