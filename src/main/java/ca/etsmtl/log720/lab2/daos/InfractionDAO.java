package ca.etsmtl.log720.lab2.daos;

import ca.etsmtl.log720.lab2.Dossier;
import ca.etsmtl.log720.lab2.Infraction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfractionDAO extends Lab2DAO {

    private final String INSERT_STATEMENT = "INSERT INTO infractions (description, gravite) VALUES (?, ?)";
    private final String READ_STATEMENT = "SELECT id, description, gravite FROM infractions where id = ?";
    private final String READ_ALL_STATEMENT = "SELECT id, description, gravite FROM infractions";
    private final String UPDATE_STATEMENT = "UPDATE infractions SET description = ?, gravite = ? WHERE id = ?";
    private final String DELETE_STATEMENT = "DELETE FROM infractions WHERE id = ?";
    private final String ALL_FOR_DOSSIER = "SELECT infractions.id, description, gravite FROM infractions INNER JOIN infraction_dossiers ON (infractions.id = infraction_id) WHERE dossier_id = ?";

    public boolean create(String description, int gravite){
        boolean retour = false;

        try{
            PreparedStatement statement = getConnection().prepareStatement(INSERT_STATEMENT);
            statement.setString(1, description);
            statement.setInt(2, gravite);
            statement.executeUpdate();
            retour = true;
        }catch (SQLException e){
            return retour;
        }

        return retour;
    }

    public Infraction read(Integer id) {
        Infraction i = null;
        if (id == null) return i;

        try {
            PreparedStatement statement = getConnection().prepareStatement(READ_STATEMENT);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                i = new Infraction();
                i.setId(rs.getInt("id"));
                i.setDescription(rs.getString("description"));
                i.setGravite(rs.getInt("gravite"));
            }
            return i;

        } catch (SQLException e) {
            return i;
        }
    }

    public List<Infraction> allForDossier(Dossier dossier) {
        List<Infraction> infractions = new ArrayList<>();

        try {
            PreparedStatement statement = getConnection().prepareStatement(ALL_FOR_DOSSIER);
            statement.setInt(1, dossier.getId());
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                Infraction i = new Infraction();
                i.setId(rs.getInt("id"));
                i.setDescription(rs.getString("description"));
                i.setGravite(rs.getInt("gravite"));

                infractions.add(i);
            }
            return infractions;
        } catch (SQLException e) {
            return infractions;
        }
    }

    public List<Infraction> readAll() {
        List<Infraction> infractions = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(READ_ALL_STATEMENT);

            while (rs.next()) {
                Infraction i = new Infraction();
                i.setId(rs.getInt("id"));
                i.setDescription(rs.getString("description"));
                i.setGravite(rs.getInt("gravite"));

                infractions.add(i);
            }

            return infractions;
        } catch (SQLException e) {
            return infractions;
        }
    }

    public boolean update(int id, int gravite, String description){
        try{
            PreparedStatement statement = getConnection().prepareStatement(UPDATE_STATEMENT);
            statement.setString(1, description);
            statement.setInt(2, gravite);
            statement.setInt(3, id);
            statement.executeUpdate();
            return true;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(Infraction infraction){
        try {
            PreparedStatement statement = getConnection().prepareStatement(DELETE_STATEMENT);
            statement.setInt(1, infraction.getId());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
