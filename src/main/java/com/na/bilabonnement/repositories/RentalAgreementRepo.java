package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.FileReply;
import com.na.bilabonnement.models.RentalAgreement;
import com.na.bilabonnement.models.RentalType;
import com.na.bilabonnement.repositories.interfaces.IRentalAgreementRepository;
import com.na.bilabonnement.utils.DatabaseConnectionManager;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalAgreementRepo implements IRentalAgreementRepository {

    private static final RentalAgreementRepo instance = new RentalAgreementRepo();
    private RentalAgreementRepo() {}
    public static RentalAgreementRepo getInstance() {
        return instance;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Override
    public RentalAgreement create(RentalAgreement entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO `bilabonnement`.`rental_agreements` (`carId`, `startDate`, `endDate`, `price`, `type`, `contract`, `filename`) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getCarId());
            stmt.setString(2, entity.getStartDate().toString());
            stmt.setString(3, entity.getEndDate().toString());
            stmt.setInt(4, entity.getPrice());
            stmt.setString(5, RentalType.values()[entity.getTypeId()].toString());

            if (entity.getContract().isEmpty()) {
                stmt.setNull(6, Types.BLOB);
            } else {
                stmt.setBlob(6,  entity.getContract().getInputStream());
                stmt.setString(7, entity.getContract().getOriginalFilename());
            }

            stmt.execute();

            stmt = conn.prepareStatement("SELECT LAST_INSERT_ID();");
            ResultSet rs = stmt.executeQuery();
            rs.next();

            entity.setId(rs.getInt("LAST_INSERT_ID()"));
            return entity;
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RentalAgreement getSingleEntityById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        String selectSQL = "SELECT id, carId, startDate, endDate, price, type FROM rental_agreements WHERE id=?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        }   catch (SQLException e) {
            e.printStackTrace();
        }

        RentalAgreement result = null;
        if (rs != null){
            result = makeRentalAgreementFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Override
    public List<RentalAgreement> getAllEntities() {
        Connection conn = DatabaseConnectionManager.getConnection();
        String selectSQL = "SELECT id, carId, startDate, endDate, price, type FROM rental_agreements;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        }   catch (SQLException e) {
            e.printStackTrace();
        }

        List<RentalAgreement> result = new ArrayList<>();
        if (rs != null){
            result = makeRentalAgreementsFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Override
    public RentalAgreement update(RentalAgreement entity) {
        Connection con = DatabaseConnectionManager.getConnection();
        String updateSQL = "UPDATE `bilabonnement`.`rental_agreements` " +
                           "SET `carId` = ?, `startDate` = ?, `endDate` = ?, `price` = ?, `type` = ?, `contract` = ?, `filename` = ? " +
                           "WHERE (`id` = ?);";


        try {
            PreparedStatement stmt = con.prepareStatement(updateSQL);
            stmt.setInt(1, entity.getCarId());
            stmt.setString(2, entity.getStartDate().toString());
            stmt.setString(3, entity.getEndDate().toString());
            stmt.setInt(4, entity.getPrice());
            stmt.setString(5, RentalType.values()[entity.getTypeId()].toString());
            stmt.setBlob(6,  entity.getContract().getInputStream());
            stmt.setString(7, entity.getContract().getOriginalFilename());
            stmt.setInt(8, entity.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return getSingleEntityById(entity.getId());
    }

    /**
     *  @author Mathias(Eliot996)
     */
    @Override
    public RentalAgreement updateWithoutContract(RentalAgreement entity) {
        Connection con = DatabaseConnectionManager.getConnection();
        String updateSQL = "UPDATE `bilabonnement`.`rental_agreements` " +
                "SET `carId` = ?, `startDate` = ?, `endDate` = ?, `price` = ?, `type` = ? WHERE (`id` = ?);";


        try {
            PreparedStatement stmt = con.prepareStatement(updateSQL);
            stmt.setInt(1, entity.getCarId());
            stmt.setString(2, entity.getStartDate().toString());
            stmt.setString(3, entity.getEndDate().toString());
            stmt.setInt(4, entity.getPrice());
            stmt.setString(5, RentalType.values()[entity.getTypeId()].toString());
            stmt.setInt(6, entity.getId());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getSingleEntityById(entity.getId());
    }

    @Override
    public FileReply loadFile(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        String selectSQL = "SELECT contract, filename FROM rental_agreements WHERE id=?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = conn.prepareStatement(selectSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        }   catch (SQLException e) {
            e.printStackTrace();
        }

        FileReply result = null;
        if (rs != null){
            try {
                rs.next();
                Resource resource = new InputStreamResource(rs.getBlob("contract").getBinaryStream());
                String name = rs.getString("filename");
                result = new FileReply(resource, name);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        DatabaseConnectionManager.closeConnection();
        return result;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    private List<RentalAgreement> makeRentalAgreementsFromResultSet(ResultSet rs) {
        ArrayList<RentalAgreement> rentalAgreements = new ArrayList<>();
        try {
            while(rs.next()) {
                int id = rs.getInt("id");
                int carId = rs.getInt("carId");
                int price = rs.getInt("price");
                LocalDate startDate = LocalDate.parse(rs.getString("startDate"));
                LocalDate endDate = LocalDate.parse(rs.getString("endDate"));
                RentalType type = RentalType.valueOf(rs.getString("type"));
                rentalAgreements.add(new RentalAgreement(id, carId, price, startDate, endDate, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentalAgreements;    }

     /**
          *  @author Arboe(H4ppyN4p)
          */
    @Override
    public boolean deleteById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM rental_agreements WHERE id=?");
            stmt.setInt(1, id);
            stmt.execute();
            DatabaseConnectionManager.closeConnection();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     *  @author Mathias(Eliot996)
     */
    private RentalAgreement makeRentalAgreementFromResultSet(ResultSet rs) {
        return makeRentalAgreementsFromResultSet(rs).get(0);
    }
}
