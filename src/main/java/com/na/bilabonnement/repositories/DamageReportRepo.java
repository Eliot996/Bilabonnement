package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DamageReportRepo implements IDamageReportRepository
    {

        private static final DamageReportRepo Instance = new DamageReportRepo();

    private DamageReportRepo(){}
        public static DamageReportRepo getInstance(){
        return instance;
    }


    @Override
    public DamageReport create(DamageReport entity)
    {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO `bilabonnement`.`damage_report` (`id`, `notes`, `technicianId`, `carId`)" +
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

    @Override
    public DamageReport getSingleEntityById(int id)
    {
        return null;
    }

    @Override
    public List<DamageReport> getAllEntities()
    {
        return null;
    }

    @Override
    public DamageReport update(DamageReport entity)
    {
        return null;
    }

    @Override
    public boolean deleteById(int id)
    {
        return false;
    }
}
