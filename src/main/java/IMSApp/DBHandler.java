package IMSApp;

import java.sql.*;

class DBHandler {

    private static Connection connect(){
        Connection maindbCon=null;
        try {
            String DBloc = "jdbc:hsqldb:file:maindb";
            maindbCon = DriverManager.getConnection(DBloc +";shutdown=true");
        } catch (java.sql.SQLException e) {
            System.out.println("error initializing database");
            e.printStackTrace();
        }
        return maindbCon;
    }

    static void deleteRec(String id) {
        try {
            PreparedStatement stmt = connect().prepareStatement("DELETE FROM inventory WHERE ID ="+id);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void createFullDB(){
        Connection c = connect();
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate("CREATE TABLE inventory(" +
                    "ID varchar(255) NOT NULL," +
                    "Desc varchar(255)," +
                    "COGS varchar(12), " +
                    "Date_Made DATE, " +
                    "Sale_Date DATE, " +
                    "Sale_Price varchar(12))"
            );
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addRec(String id, String desc, String cogs, String dateMade) throws SQLException {
        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.executeQuery("INSERT INTO inventory (" +
                "ID, Desc, COGS, Date_Made) VALUES " +
                "('" + id + "','" + desc + "','" + cogs + "', '" + dateMade + "')");
        c.close();
    }

    static void soldRec(String id, String saleDate, String salePrice) throws java.sql.SQLException {
        Connection c = connect();
        Statement stmt = c.createStatement();
        stmt.executeQuery("UPDATE inventory SET " + "Sale_Date = '" + saleDate + "' , " + "Sale_Price = '" + salePrice +
                "WHERE ID = " + id);
        c.close();
    }

    static void update(String[] items) {
        Connection c = connect();
        try{
            PreparedStatement stmt = c.prepareStatement("UPDATE inventory SET " +
                    "Desc = ?, COGS = ?, Date_Made = ?, Sale_Date = ?, Sale_Price = ? WHERE ID = ?");

            stmt.setString(1, items[1]);
            stmt.setString(2, items[2]);
            stmt.setString(3, items[3]);
            stmt.setString(4, items[4]);
            stmt.setString(5, items[5]);
            stmt.setString(6, items[0]);

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static ResultSet exportFromDates(String from, String to, String status){
        String q = "SELECT * FROM inventory WHERE "+status+" BETWEEN '"+from+"' AND '"+to+"'";
        Connection c = connect();
        try {
            Statement stmt = c.createStatement();
            return stmt.executeQuery(q);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    static String[] searchID(String id) {
        Connection c = connect();
        String q = "SELECT * FROM inventory WHERE ID ='" + id +" '";
        String[] results = new String[7];
        String[] cols = {"ID", "Desc", "COGS", "Date_Made", "Sale_Date", "Sale_Price"};
        try {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            while (rs.next()) {
                for (int i = 0; i < 6; i++) {
                    results[i] = rs.getString(cols[i]);
                }
            }
        } catch (Exception e ){
            e.printStackTrace();
        }
        return results;
    }

    static ResultSet exportFromID(String id){
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

    static ResultSet exportEverything(){
        try {
            Statement stmt = connect().createStatement();
            return stmt.executeQuery("SELECT * FROM inventory");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}