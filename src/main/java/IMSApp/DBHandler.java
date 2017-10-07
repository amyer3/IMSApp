package IMSApp;

import java.sql.*;

public class DBHandler {
    public static String DBloc = "jdbc:hsqldb:file:maindb";

    public static void main(String[] args) {}

    public static void Shutdown(Connection c){
        try {
            DriverManager.getConnection("jdbc:hsqldb:file:maindb;shutdown=true");
        } catch (java.sql.SQLException e){
            System.out.println("could not shut down properly");
        }
    }

    public static Connection connect(){
        Connection maindbCon = null;
        try {
            maindbCon = DriverManager.getConnection(DBloc);
        } catch (java.sql.SQLException e) {
            System.out.println("error initializing database");
            e.printStackTrace();
        }
        return maindbCon;
    }

    public static void deleteRec(Connection c, String id) throws java.sql.SQLException{} //TODO delete a record

    public static void createFullDB(Connection c){
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate("CREATE TABLE inventory " +
                    "(ID varchar(255) NOT NULL," +
                    "Desc varchar(255)," +
                    "COGS float, " +
                    "Date_Made DATE, " +
                    "Sold boolean, " +
                    "Sale_Date DATE)");
            System.out.println("Table Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRec(Connection c, String[] items) throws java.sql.SQLException {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("INSERT INTO inventory (ID varchar(255) NOT NULL, Desc varchar(255), COGS float, " +
                "Date_Made DATE, Sold boolean) VALUES " +
                "("+items[0]+items[1]+items[2]+items[3]+"false)");
    } //TODO unexpected token VARCHAR

    public void soldRec(Connection c, String[] items) {} //TODO updates for sold

    public void update(Connection c, String[] items) {
    }

    public void searchDates(Connection c){}

    public void searchID(Connection c){}
}
