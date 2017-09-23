package com.IMSApp;

import java.sql.*;

public class DBHandler {
    public static String DBloc = "jdbc:hsqldb:file:maindb";

    //public static void main(String[] args) {}

    public static void pullData(){
        ResultSet rs = null;
    }

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

    public static void addRec(){
        // TODO
    }

    public static void viewRec(){
        //TODO
    }

    public static Connection tempConnect(){
        Connection maindbCon = null;
        try {
            maindbCon = DriverManager.getConnection("jdbc:hsqldb:mem:maindb");
            System.out.println("database memory connection");
            System.out.println(maindbCon.getMetaData());
        } catch (java.sql.SQLException e) {
            System.out.println("error initializing database");
            e.printStackTrace();
        }
        return maindbCon;
    }
}
