package ca.etsmtl.log720.lab2.daos;

import ca.etsmtl.log720.lab2.Dossier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DossierDAO {

    private final String INSERT_STATEMENT   = "INSERT INTO dossiers (nom, prenom, no_plaque, no_permis) VALUES (?, ?, ?, ?)";
    private final String READ_STATEMENT     = "SELECT id, nom, prenom, no_plaque, no_permis FROM dossiers where id = ?";
    private final String READ_ALL_STATEMENT = "SELECT id, nom, prenom, no_plaque, no_permis FROM dossiers";
    private final String UPDATE_STATEMENT   = "UPDATE dossiers SET nom = ?, prenom = ?, no_plaque = ?, no_permis = ? WHERE id = ?";
    private final String DELETE_STATEMENT   = "DELETE FROM dossiers WHERE id = ?";

    public Dossier create(String nom, String prenom, String noPlaque, String noPermis) {
        Dossier d = new Dossier();
        Connection connection = Lab2DAOFactory.createConnection();
        if(connection == null) {
            return d;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, noPlaque);
            statement.setString(4, noPermis);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setNoPlaque(rs.getString("no_plaque"));
                d.setNoPermis(rs.getString("no_permis"));
            }
            return d;
        } catch (SQLException e) {
            return d;
        }
    }

    public Dossier read(int id) {
        Dossier d = new Dossier();
        Connection connection = Lab2DAOFactory.createConnection();
        if (connection == null) {
            return d;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(READ_STATEMENT);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
                d.setPrenom(rs.getString("prenom"));
                d.setNoPlaque(rs.getString("no_plaque"));
                d.setNoPermis(rs.getString("no_permis"));
            }

            return d;
        } catch (SQLException e) {
            return d;
        }
    }

    public List<Dossier> readAll() {
        List<Dossier> dossiers = new ArrayList<>();
        Connection connection = Lab2DAOFactory.createConnection();
        if(connection == null) {
            return dossiers;
        }

        try {
            Statement statement = connection.createStatement();
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

            return dossiers;
        } catch (SQLException e) {
            return dossiers;
        }
    }

    public boolean update(int id, String nom, String prenom, String noPlaque, String noPermis) {
        Connection connection = Lab2DAOFactory.createConnection();
        if(connection == null) {
            return false;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, noPlaque);
            statement.setString(4, noPermis);
            statement.setInt(5, id);
            return statement.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean delete(Dossier dossier) {
        Connection connection = Lab2DAOFactory.createConnection();
        if(connection == null) {
            return false;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_STATEMENT);
            statement.setInt(1, dossier.getId());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
