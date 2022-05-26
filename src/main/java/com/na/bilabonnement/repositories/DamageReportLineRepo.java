package com.na.bilabonnement.repositories;
import com.na.bilabonnement.models.DamageReportLine;
import com.na.bilabonnement.repositories.interfaces.IDamageReportLineRepository;
import com.na.bilabonnement.utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String maxSQL = "SELECT max(lineNumber) AS aValue FROM damageline WHERE damageReportId = ?";

        String insertSQL = "INSERT INTO `bilabonnement`.`damageline` (`linenumber`, `damageReportId`, `damageNotes`, `price`)" +
                "VALUES (?, ?, ?, ?);";

        try {
            PreparedStatement stmt = connection.prepareStatement(maxSQL);
            stmt.setInt(1,entity.getDamageReportId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            entity.setLineNumber( rs.getInt("aValue") + 1);

            stmt = connection.prepareStatement(insertSQL);
            stmt.setInt(1, entity.getLineNumber());
            stmt.setInt(2, entity.getDamageReportId());
            stmt.setString(3, entity.getDamageNotes());
            stmt.setInt(4, entity.getPrice());

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getSingleEntityByLinenumberAndDamageReportId(entity.getLineNumber(), entity.getDamageReportId());
    }

    public DamageReportLine getSingleEntityByLinenumberAndDamageReportId(int lineNumber, int damageReportId)
    {
        Connection connection = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM damageline " +
                "WHERE `lineNumber` = ? AND `damageReportId` = ?;";
        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(selectSQL);
            stmt.setInt(1, lineNumber);
            stmt.setInt(2, damageReportId);
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
                int lineNumber = rs.getInt("linenumber");
                int damageReportId = rs.getInt("damageReportId");
                String damageNotes = rs.getString("damageNotes");
                int price = rs.getInt("price");
                damageReportLines.add(new DamageReportLine(damageReportId, lineNumber, damageNotes, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return damageReportLines;
    }

    @Override
    public DamageReportLine getSingleEntityById(int id)
    {
        Connection connection = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM damageline WHERE `lineNumber` = ?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(selectSQL);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DamageReportLine result = null;
        if (rs != null) {
            result = makeDamageReportLineFromResultSet(rs);
        }

        DatabaseConnectionManager.closeConnection();
        return result;
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
        Connection connection = DatabaseConnectionManager.getConnection();

        String insertSQL = "UPDATE `bilabonnement`.`damageline`" +
                "SET `damageNotes` = ?, `price` = ? " +
                "WHERE `lineNumber` = ? AND `damageReportId` = ?;";


        try {
            PreparedStatement stmt = connection.prepareStatement(insertSQL);
            stmt.setString(1,entity.getDamageNotes());
            stmt.setInt(2,entity.getPrice());
            stmt.setInt(3, entity.getLineNumber());
            stmt.setInt(4, entity.getDamageReportId());

            stmt.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean deleteById(int lineNumber, int damageReportId){
        Connection connection = DatabaseConnectionManager.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM damageline where lineNumber=? AND damageReportId=?");
            stmt.setInt(1, lineNumber);
            stmt.setInt(2, damageReportId);
            stmt.execute();

            DatabaseConnectionManager.closeConnection();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return false;

    }

    @Override
    public List<DamageReportLine> getAllEntitiesWithDamageReportId(int damageReportId) {
        Connection connection = DatabaseConnectionManager.getConnection();

        String selectSQL = "SELECT * FROM damageline WHERE `damageReportId` = ?;";

        ResultSet rs = null;
        try {
            PreparedStatement stmt = connection.prepareStatement(selectSQL);
            stmt.setInt(1, damageReportId);
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
