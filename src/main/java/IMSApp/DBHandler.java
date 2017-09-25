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

    public static void addRec(Connection c) throws java.sql.SQLException {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery("INSERT INTO inv (row1, row2) VALUES ('a', 'b')");
    }

    public static void viewRec(Connection c) throws java.sql.SQLException{
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
    }

    public static Connection tempConnect(){
        Connection maindbCon = null;
        try {
            maindbCon = DriverManager.getConnection("jdbc:hsqldb:mem:maindb");
            System.out.println("database memory connection");
            Statement stmt = maindbCon.createStatement();
            stmt.executeUpdate("CREATE TABLE inv (row1 varchar(225), row2 varchar(225))");
            System.out.println("Table Created");

            System.out.println(maindbCon.getMetaData());
        } catch (java.sql.SQLException e) {
            System.out.println("error initializing database");
            e.printStackTrace();
        }
        return maindbCon;
    }
}
