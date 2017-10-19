package IMSApp;

import java.sql.*;

public class DBHandler {
    static String DBloc = "jdbc:hsqldb:file:maindb";

    public static void Shutdown(){
        try {
            DriverManager.getConnection(DBloc+";shutdown=true");
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection connect(){
        Connection maindbCon=null;
        try {
            maindbCon = DriverManager.getConnection(DBloc+";shutdown=true");
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
                    "Sale_Date DATE, " +
                    "Sale_Price float," +
                    "Sold boolean)"
            );
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRec(String[] items) {
        Connection c = connect();
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO inventory (" +
                    "ID, Desc, COGS, Date_Made, Sold) VALUES " +
                    "('" + items[0] + "','" + items[1] + "','" + items[2] + "', '" + items[3] + "','false')");
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void soldRec(String[] items) {
        //String[] items = {idText.getText(), saleDateText.getDate().toString(), salePriceText.getText()};
        Connection c = connect();
        try{
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("UPDATE inventory SET " +
                    "Sale_Date = '" + items[1]+"' , "+
                    "Sale_Price = '" + items[2]+"' "+
                    "Sold = 'true' "+
                    "WHERE ID = "+items[0]);

            c.close();
        }
        catch(java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(String[] items) {
        Connection c = connect();
        try{
            Statement stmt = c.createStatement();
            //user lacks privilege or object not found: WHERE
            ResultSet rs = stmt.executeQuery("UPDATE inventory SET " +
                    "Desc = '" + items[1]+"' , "+
                    "COGS = '" + items[2]+"' , "+
                    "Date_Made = '" + items[3]+"' , "+
                    "Sale_Date = '" + items[4]+"' , "+
                    "Sale_Price = '" + items[5]+"' "+
                    "WHERE ID = "+items[0]);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet exportFromDates(String from, String to, String status){
        String q = "SELECT * FROM inventory WHERE";
        Connection c = connect();
        try {
            Statement stmt = c.createStatement();
            return stmt.executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] searchID(String id){
        Connection c = connect();
        String q = "SELECT * FROM inventory WHERE ID ='" + id +" '";
        String[] results = new String[7];
        String[] cols = {"ID", "Desc", "COGS", "Date_Made", "Sold", "Sale_Date", "Sale_Price"};
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()){
                for (int i = 0; i <  7; i++) {
                    results[i] = rs.getString(cols[i]);

                }
            }
        } catch (SQLException e) {
            System.out.println("Record not Found (DBHandler.searchID)");
        }
        return results;
    }

    public static ResultSet exportFromID(String id){
        String q = "SELECT * FROM inventory WHERE ID ='"+id+"'";
        Connection c = connect();
        try {
            Statement stmt = c.createStatement();
            return stmt.executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}