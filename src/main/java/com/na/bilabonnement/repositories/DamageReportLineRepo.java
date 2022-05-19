package com.na.bilabonnement.repositories;

import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
public class DamageReportLineRepo implements IDamageReportLineRepository
{
    private static final DamageReportLineRepo instance = new DamageReportLineRepo();

    private DamageReportLineRepo(){}
    public static DamageReportLineRepo getInstance(){return instance;}


    @Override
    public DamageReportLine create(DamageReportLine entity)
    {
        Connection connection = DatabaseConnectionManager.getConnection();
        String insertSQL = "INSERT INTO `bilabonnement`.`damageline` (`linenumber`, `damageReportId`, `damageNotes`, `price`)" +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement stmt = connection.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getLineNumber());
            stmt.setInt(2, entity.getDamageReportId());
            stmt.setString(3, entity.getDamageNotes());
            stmt.setInt(4, entity.getPrice());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getSingleEntityByDamageReportId(entity.getDamageReportId());
    }

    public DamageReportLine getSingleEntityByDamageReportId(int damageReportId)
    {
        Connection connection = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM damageline " +
                "WHERE `damageReportId` = '" + damageReportId +  "';";
        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(selectSQL);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        DamageReportLine result = null;
        if (rs != null){
            result = makeDamageReportLineFromResultSet(rs);
        }
        DatabaseConnectionManager.closeConnection();
        return result;
    }

    private DamageReportLine makeDamageReportLineFromResultSet(ResultSet rs) {

        List<DamageReportLine> damageReportLines = makeDamageReportLinesFromResultSet(rs);
        if (damageReportLines.size() > 0) {
            return damageReportLines.get(0);
        }
        return null;
    }


    private List<DamageReportLine> makeDamageReportLinesFromResultSet(ResultSet rs) {
        ArrayList<DamageReportLine> damageReportLines = new ArrayList<>();
        try {
            while (rs.next()){
                int lineNumber = rs.getInt("linenumer");
                int damageReportId = rs.getInt("damagereportId");
                String damageNotes = rs.getString("damageNotes");
                int price = rs.getInt("price");
                damageReportLines.add(new DamageReportLine(lineNumber, damageReportId, damageNotes, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return damageReportLines;
    }

    @Override
    public DamageReportLine getSingleEntityById(int id)
    {
        return null;
    }

    @Override
    public List<DamageReportLine> getAllEntities()
    {
        {
            Connection connection = DatabaseConnectionManager.getConnection();

            String selectSQL = "SELECT * FROM damageline;";

            ResultSet rs = null;
            try {
                PreparedStatement stmt = connection.prepareStatement(selectSQL);
                rs = stmt.executeQuery();
            } catch (SQLException e){
                e.printStackTrace();
            }

            List<DamageReportLine> result = new ArrayList<>();
            if (rs != null){
                result = makeDamageReportLinesFromResultSet(rs);
            }

            DatabaseConnectionManager.closeConnection();
            return result;
        }
    }

    @Override
    public DamageReportLine update(DamageReportLine entity)
    {
        return null;
    }

    @Override
    public boolean deleteById(int id)
    {
        return false;
    }
}
