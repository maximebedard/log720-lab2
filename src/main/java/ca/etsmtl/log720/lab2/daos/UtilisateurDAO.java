package ca.etsmtl.log720.lab2.daos;

import ca.etsmtl.log720.lab2.Dossier;
import ca.etsmtl.log720.lab2.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtilisateurDAO extends Lab2DAO {

    private final String INSERT_STATEMENT          = "INSERT INTO USERS (username, pwd) VALUES (?, ?)";
    private final String READ_STATEMENT            = "SELECT id, username FROM USERS where id = ?";
    private final String READ_ALL_STATEMENT        = "SELECT id, username FROM USERS";
    private final String DELETE_STATEMENT          = "DELETE FROM USERS WHERE id = ?";
    private final String UNIQUE_CHECK              = "SELECT * FROM USERS WHERE username = ?";
    private final String INSERT_RIGHTS             = "INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES (?,?)";
    private final String READ_USER_ID              = "SELECT ID FROM USERS WHERE USERNAME = ?";

    public boolean create(String username, String password, int right) {
        try {
            PreparedStatement uniquenessCheck = getConnection().prepareStatement(UNIQUE_CHECK);
            uniquenessCheck.setString(1, username);
            ResultSet rsCheck = uniquenessCheck.executeQuery();

            if(rsCheck.next()){
                return false;
            }

            PreparedStatement statement = getConnection().prepareStatement(INSERT_STATEMENT);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();

            statement = getConnection().prepareStatement(READ_USER_ID);
            statement.setString(1, username);
            rsCheck = statement.executeQuery();
            Integer id = -1;
            if(rsCheck.next()){
                    id = rsCheck.getInt("ID");
            }else{
                return false;
            }

            statement = getConnection().prepareStatement(INSERT_RIGHTS);
            statement.setInt(1, id);
            statement.setInt(2, right);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Utilisateur read(Integer id) {
        Utilisateur d = null;
        if (id == null) return d;

        try {
            PreparedStatement statement = getConnection().prepareStatement(READ_STATEMENT);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                d = new Utilisateur();
                d.setId(rs.getInt("id"));
                d.setNom(rs.getString("nom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    public List<Utilisateur> readAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        try {
            PreparedStatement statement = getConnection().prepareStatement(READ_ALL_STATEMENT);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {

                    Utilisateur d = new Utilisateur();
                    d.setId(rs.getInt("id"));
                    d.setNom(rs.getString("username"));
                    utilisateurs.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }


    public boolean delete(Utilisateur utilisateur) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(DELETE_STATEMENT);
            statement.setInt(1, utilisateur.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
