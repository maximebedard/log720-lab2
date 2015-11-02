package ca.etsmtl.log720.lab2.daos;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.Logger;

public class Lab2DAO {

    public class ConnectionFailedException extends RuntimeException {}

    private final Connection connection;

    public Lab2DAO() {
        connection = createConnection();
    }

    private Connection createConnection() {
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) webContext.lookup("jdbc/pg");
            return ds.getConnection();
        } catch (NamingException e) {
            throw new ConnectionFailedException();
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    public static DossierDAO getDossierDAO() {
        return new DossierDAO();
    }

    public static InfractionDAO getInfractionDAO(){
        return new InfractionDAO();
    }

    public Connection getConnection() {
        return connection;
    }
}
