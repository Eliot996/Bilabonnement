package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.RentalAgreement;


import java.util.ArrayList;
import java.util.List;

public class DummyRentalAgreementRepo implements IRentalAgreementRepository{

    private ArrayList<RentalAgreement> list = new ArrayList<>();


    @Override
    public RentalAgreement create(RentalAgreement entity) {
        list.add(entity);
        entity.setId(list.indexOf(entity));
        return entity;
    }

    @Override
    public RentalAgreement getSingleEntityById(int id) {
        for (RentalAgreement ra : list) {
            if (ra.getId() == id) {
                return ra;
            }
        }
        return null;
    }

    @Override
    public List<RentalAgreement> getAllEntities() {
        return list;
    }

    @Override
    public RentalAgreement update(RentalAgreement entity) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        for (RentalAgreement ra : list) {
            if (ra.getId() == id) {
                list.remove(ra);
                return true;
            }
        }
        return false;
    }
}
