package ca.etsmtl.log720.lab2.daos;

import ca.etsmtl.log720.lab2.Dossier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DossierDAO extends Lab2DAO {

    private final String INSERT_STATEMENT          = "INSERT INTO dossiers (nom, prenom, no_plaque, no_permis) VALUES (?, ?, ?, ?)";
    private final String READ_STATEMENT            = "SELECT id, nom, prenom, no_plaque, no_permis FROM dossiers where id = ?";
    private final String READ_ALL_STATEMENT        = "SELECT id, nom, prenom, no_plaque, no_permis FROM dossiers";
    private final String UPDATE_STATEMENT          = "UPDATE dossiers SET nom = ?, prenom = ?, no_plaque = ?, no_permis = ? WHERE id = ?";
    private final String DELETE_STATEMENT          = "DELETE FROM dossiers WHERE id = ?";
    private final String CREATE_DOSSIER_INFRACTION = "INSERT INTO dossier_infractions (dossier_id, infraction_id) VALUES (?, ?)";

    public Dossier create(String nom, String prenom, String noPlaque, String noPermis) {
        Dossier d = null;

        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_STATEMENT);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, noPlaque);
            statement.setString(4, noPermis);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                d = new Dossier();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setNoPlaque(rs.getString("no_plaque"));
                d.setNoPermis(rs.getString("no_permis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public Dossier read(Integer id) {
        Dossier d = null;
        if (id == null) return d;

        try {
            PreparedStatement statement = getConnection().prepareStatement(READ_STATEMENT);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                d = new Dossier();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setNoPlaque(rs.getString("no_plaque"));
                d.setNoPermis(rs.getString("no_permis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public List<Dossier> readAll() {
        List<Dossier> dossiers = new ArrayList<>();

        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery(READ_ALL_STATEMENT);

            while(rs.next()) {
                Dossier d = new Dossier();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setNoPlaque(rs.getString("no_plaque"));
                d.setNoPermis(rs.getString("no_permis"));
                dossiers.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dossiers;
    }

    public boolean update(int id, String nom, String prenom, String noPlaque, String noPermis) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(UPDATE_STATEMENT);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, noPlaque);
            statement.setString(4, noPermis);
            statement.setInt(5, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createInfractionsForDossier(Integer id, String[] infractions) {
        if(id == null) return false;
        try {
            for(String infraction: infractions){
                PreparedStatement statement = getConnection().prepareStatement(CREATE_DOSSIER_INFRACTION);
                statement.setInt(1, id);
                statement.setInt(2, Integer.valueOf(infraction));
                statement.execute();
            }
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean delete(Dossier dossier) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(DELETE_STATEMENT);
            statement.setInt(1, dossier.getId());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
