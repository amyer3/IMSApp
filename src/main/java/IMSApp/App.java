package IMSApp;

import java.sql.Connection;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws java.sql.SQLException {
        // connect to database
        Connection maindb;
        maindb = DBHandler.tempConnect();
        System.out.println(maindb.getMetaData());
        Scanner input = new Scanner(System.in);
        System.out.println("Adding values to table");
        //String res = input.next();
        //System.out.println(res);
        DBHandler.addRec(maindb);
        DBHandler.viewRec(maindb);


    }
}
