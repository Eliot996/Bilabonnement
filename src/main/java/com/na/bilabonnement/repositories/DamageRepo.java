package com.na.bilabonnement.repositories;
import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.models.DamageReport;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DamageRepo implements IDamageReportRepository
{

    private static final DamageRepo Instance = new DamageRepo();

    private DamageRepo(){}
    public static DamageRepo getInstance(){
        return null;
    }


    @Override
    public DamageReport create(DamageReport entity)
    {
        Connection conn = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO `bilabonnement`.`damage_report` (`id`, `notes`, `technicianId`, `carId`)" +
                ""


        return null;
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
