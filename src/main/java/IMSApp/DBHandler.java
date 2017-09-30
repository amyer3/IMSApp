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
            System.out.println("database file connected");
        } catch (java.sql.SQLException e) {
            System.out.println("error initializing database");
            e.printStackTrace();
        }
        return maindbCon;
    }

    public static void destroy(Connection c){
        try {
            PreparedStatement psmt = c.prepareStatement("DROP SCHEMA");
            psmt.execute();
            System.out.println("database destroyed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRec(Connection c, String id) throws java.sql.SQLException{} //TODO delete a record

    public static void createFullDB(Connection c){
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate("CREATE TABLE inventory (ID varchar(255) NOT NULL, Desc varchar(255), COGS float, " +
                    "Date_Made DATE, Sold boolean, Sale_Date DATE)");
            System.out.println("Table Created");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void addRec(Connection c, String[] items) throws java.sql.SQLException {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("INSERT INTO inventory (ID varchar(255) NOT NULL, Desc varchar(255), COGS float, " +
                "Date_Made DATE, Sold boolean, Sale_Date DATE) VALUES " +
                "("+items[0]+items[1]+items[2]+items[3]+items[4]+items[5]+")");
    } //TODO initial adding

    public static void viewRec(Connection c, String id) throws java.sql.SQLException{
        String sql = "SELECT row1, row2 FROM inv";
        Statement stmt = c.createStatement();
        ResultSet rs2 = stmt.executeQuery(sql);
        String b = null;
        String ai = null;
        while (rs2.next()) {
            ai = rs2.getString("row1");
            b = rs2.getString("row2");
        }
        rs2.close();
        System.out.println(ai + " " + b);
    } //TODO prints reports

    public void soldRec(Connection c, String[] items) {} //TODO updates for sold

    public void update(Connection c, String[] items) {
    }
}
