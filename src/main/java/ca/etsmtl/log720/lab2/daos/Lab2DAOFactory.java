package ca.etsmtl.log720.lab2.daos;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.logging.Logger;

public class Lab2DAOFactory {
    public static Connection createConnection() {
        Connection dbCon = null;
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");

            DataSource ds = (DataSource) webContext.lookup("jdbc/pg");
            dbCon = ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
            Logger.getGlobal().severe(e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getGlobal().severe(e.getMessage());
        }
        return dbCon;
    }

    public static DossierDAO getDossierDAO() {
        return new DossierDAO();
    }

    public static InfractionDAO getInfractionDAO(){
        return new InfractionDAO();
    }
}
