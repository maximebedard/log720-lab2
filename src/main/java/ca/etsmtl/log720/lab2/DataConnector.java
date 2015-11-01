package ca.etsmtl.log720.lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by ALEXANDRE on 2015-11-01.
 */
public class DataConnector {

    private Connection con;
    private String DB_URL = "log720.logti.etsmtl.ca";
    private String DB_USER = "am05130";
    private String DB_PW = "bob12";
    public DataConnector(){

        try {

            Class.forName("org.postgresql.Driver");


        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        try{
            con = DriverManager.getConnection(DB_URL,DB_USER,DB_PW);
        }catch (SQLException sql){
            System.out.println(" ==== can't connect to DB ==== ");
            System.out.println(sql.getMessage());
        }
    }


    public Dossier getDossierByID(int dossierID){
        Dossier returnFolder = null;



        return returnFolder;
    }
}
