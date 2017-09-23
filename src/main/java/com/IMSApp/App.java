package com.IMSApp;

import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws java.sql.SQLException {
        // connect to database
        Connection maindb;
        maindb = DBHandler.tempConnect();
        System.out.println(maindb.getMetaData());
    }
}
