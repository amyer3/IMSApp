package com.IMSApp;

import java.sql.Connection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws java.sql.SQLException {
        Connection maindb = null;
        maindb = DBHandler.connect();
        System.out.println(maindb.getMetaData());
    }
}
