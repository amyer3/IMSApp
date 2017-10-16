package IMSApp;

import java.sql.*;

public class DBHandler {
    static String DBloc = "jdbc:hsqldb:file:maindb";

    public static void Shutdown(){
        try {
            DriverManager.getConnection(DBloc+";shutdown=true");
            System.out.println("Save and exited");
        } catch (java.sql.SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection connect(){
        Connection maindbCon=null;
        try {
            maindbCon = DriverManager.getConnection(DBloc);
            System.out.println(maindbCon.getMetaData());
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

    public void soldRec(String[] items) {
        //String[] items = {idText.getText(), saleDateText.getDate().toString(), salePriceText.getText()};
        Connection c = connect();
        try{
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT FROM inventory");

            c.close();
        }
        catch(java.sql.SQLException e){
            e.printStackTrace();
        }
    } //TODO updates for sold

    public void update(String[] items) {
        Connection c = connect();
    }

    public String[] searchDates(String from, String to){
        Connection c = connect();
        return null;
    }

    public String[] searchID(String id){
        Connection c = connect();
        return null;
    }

}
