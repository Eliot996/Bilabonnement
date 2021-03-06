package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.repositories.interfaces.IRepository;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DamageReportRepo implements IRepository<DamageReport> {

    private static final DamageReportRepo instance = new DamageReportRepo();

    private DamageReportRepo() {
    }

    public static DamageReportRepo getInstance() {
        return instance;
    }

    /**
     *  @author Lasse
     */
    @Override
    public DamageReport create(DamageReport entity) {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO damage_report (`id`, `notes`, `technicianId`, `carId`)" +
                "VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getNotes());
            stmt.setInt(3, entity.getTechnicianId());
            stmt.setInt(4, entity.getCarId());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return getSingleEntityById(entity.getId());
    }
    /**
     *  @author Lasse
     */
    @Override
    public DamageReport getSingleEntityById(int id) {
        Connection con = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM damage_report WHERE `id` = ?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DamageReport result = null;
        if (rs != null) {
            result = makeDamageReportFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;

    }

    /**
     *  @author Lasse
     */
    private DamageReport makeDamageReportFromResultSet(ResultSet rs) {
        List<DamageReport> damageReports = makeDamageReportsFromResultSet(rs);
        if (damageReports.size() > 0) {
            return damageReports.get(0);
        }
        return null;
    }

    /**
     *  @author Lasse
     */
    private List<DamageReport> makeDamageReportsFromResultSet(ResultSet rs) {
        ArrayList<DamageReport> damageReports = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("id");
                String notes = rs.getString("notes");
                int technicianId = rs.getInt("technicianId");
                int carId = rs.getInt("carId");

                damageReports.add(new DamageReport(id, notes, technicianId, carId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damageReports;
    }


    /**
     * @author Lasse
    */
    @Override
    public List<DamageReport> getAllEntities() {
        {
            Connection con = DatabaseConnectionManager.getConnection();

            String selectSQL = "SELECT * FROM damage_report;";

            ResultSet rs = null;
            try {
                PreparedStatement stmt = con.prepareStatement(selectSQL);
                rs = stmt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List<DamageReport> result = new ArrayList<>();
            if (rs != null) {
                result = makeDamageReportsFromResultSet(rs);
            }

            DatabaseConnectionManager.closeConnection();
            return result;
        }
    }

    /**
     *  @author Lasse
     */
    @Override
    public DamageReport update(DamageReport entity) {
        Connection con = DatabaseConnectionManager.getConnection();

        String insertSQL = "UPDATE `bilabonnement`.`damage_report`" +
                "SET `notes` = ?,  `technicianId` = ?, `carId` = ? " +
                "WHERE (`id` = ?);";

        try {
            PreparedStatement stmt = con.prepareStatement(insertSQL);
            stmt.setString(1, entity.getNotes());
            stmt.setInt(2, entity.getTechnicianId());
            stmt.setInt(3, entity.getCarId());

            stmt.setInt(4, entity.getId());

            System.out.println(stmt);

            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  @author Lasse
     */
    @Override
    public boolean deleteById(int id) {
        Connection conn = DatabaseConnectionManager.getConnection();
        try {
            // delete all the damageLines before the deletion of the damageReport
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM damageline WHERE damageReportId=?");
            stmt.setInt(1, id);
            stmt.execute();

            // and then delete the damageReport
            stmt = conn.prepareStatement("DELETE FROM damage_report WHERE id=?");
            stmt.setInt(1, id);
            stmt.execute();

            DatabaseConnectionManager.closeConnection();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
