package ca.etsmtl.log720.lab2.daos;

import ca.etsmtl.log720.lab2.Infraction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InfractionDAO {

    private final String INSERT_STATEMENT = "INSERT INTO infractions (description, gravite) VALUES (?, ?)";
    private final String READ_STATEMENT = "SELECT id, description, gravite FROM infractions where id = ?";
    private final String READ_ALL_STATEMENT = "SELECT id, description, gravite FROM infractions";
    private final String UPDATE_STATEMENT = "UPDATE infractions SET description = ?, gravite = ? WHERE id = ?";
    private final String DELETE_STATEMENT = "DELETE FROM infractions WHERE id = ?";

    public Infraction create(String description, int gravite){
        Infraction inf = new Infraction();
        Connection connection = Lab2DAOFactory.createConnection();
        if(connection == null) {
            return inf;
        }

        try{
            PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT);
            statement.setString(1, description);
            statement.setInt(2, gravite);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                inf.setId(rs.getInt("id"));
                inf.setDescription(rs.getString("description"));
                inf.setGravite(rs.getInt("gravite"));
            }
            return inf;
        }catch (SQLException e){
            return inf;
        }
    }

    public Infraction read(int id) {
        Infraction i = new Infraction();
        Connection connection = Lab2DAOFactory.createConnection();

        if (connection == null) {
            return i;
        }

        try {
            PreparedStatement statement = connection.prepareStatement(READ_STATEMENT);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                i.setId(rs.getInt("id"));
                i.setDescription(rs.getString("description"));
                i.setGravite(rs.getInt("gravite"));
            }
            return i;

        } catch (SQLException e) {
            return i;
        }
    }

    public List<Infraction> readAll() {
        List<Infraction> infractions = new ArrayList<>();
        Connection connection = Lab2DAOFactory.createConnection();


        if (connection == null) {
            return infractions;
        }

        try {
            Statement statement = connection.createStatement();
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
}
