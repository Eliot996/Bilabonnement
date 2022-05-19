package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.models.RentalType;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class RentalAgreementRepo implements IRentalAgreementRepository{

    /**
     *  @author Mathias(Eliot996)
     */
    @Override
    public RentalAgreement create(RentalAgreement entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO `bilabonnement`.`rental_agreements` (`carId`, `startDate`, `endDate`, `price`, `type`, `contract`) " +
                           "VALUES (?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getCarId());
            stmt.setString(2, entity.getStartDate().toString());
            stmt.setString(3, entity.getEndDate().toString());
            stmt.setInt(4, entity.getPrice());
            stmt.setString(5, RentalType.values()[entity.getTypeId()].toString());

            if (entity.getContract().isEmpty()) {
                System.out.println("file was empty");
                stmt.setNull(6, Types.BLOB);
            } else {
                stmt.setBlob(6,  entity.getContract().getInputStream());
            }

            stmt.execute();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    public RentalAgreement getSingleEntityById(int id) {
        return null;
    }

    @Override
    public List<RentalAgreement> getAllEntities() {
        return null;
    }

    @Override
    public RentalAgreement update(RentalAgreement entity) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }


}
