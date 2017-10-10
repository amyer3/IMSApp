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

    public static void deleteRec(String id) {
        Connection c = connect();
    } //TODO delete a record

    public static void createFullDB(){
        Connection c = connect();
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate("CREATE TABLE inventory(" +
                    "ID varchar(255) NOT NULL," +
                    "Desc varchar(255)," +
                    "COGS float, " +
                    "Date_Made DATE, " +
                    "Sold boolean, " +
                    "Sale_Date DATE)"
            );
            System.out.println("Table Created");
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRec(String[] items) throws java.sql.SQLException {
        Connection c = connect();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("INSERT INTO inventory (ID varchar(255) NOT NULL, Desc varchar(255), COGS float, " +
                "Date_Made DATE, Sold boolean) VALUES " +
                "("+items[0]+items[1]+items[2]+items[3]+"false)");
        c.close();
    } //TODO unexpected token VARCHAR

    public void soldRec(String[] items) {
        Connection c = connect();
    } //TODO updates for sold

    public void update(String[] items) {
        Connection c = connect();
    }

    public void searchDates(String from, String to){
        Connection c = connect();
    }

    public void searchID(String id){
        Connection c = connect();
    }

}
