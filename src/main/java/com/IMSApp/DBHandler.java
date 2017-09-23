package com.IMSApp;

import java.sql.*;

public class DBHandler {
    public static String DBloc = "jdbc:hsqldb:file:maindb";

    public static void main(String[] args) throws ClassNotFoundException {
        Connection maindbCon = null;
        try {
            maindbCon = DriverManager.getConnection(DBloc);
            System.out.println("database connected");
            System.out.println(maindbCon.getMetaData());
        } catch (java.sql.SQLException e) {
            System.out.println("error initializing database");
            e.printStackTrace();
        }
    }
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
}
