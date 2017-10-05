package IMSApp;
import java.io.*;
import java.sql.*;


public class Operations {

    public static void main(String[] args) throws java.io.IOException, java.sql.SQLException{

    }
    public static Connection firstRun() throws java.io.IOException, SQLException {

        File statusFile = new File("src/main/statusFileFolder/statusFile.txt");
        boolean firstRun = false;
        if(!statusFile.exists()){
            statusFile.createNewFile();
            Connection NewConn = DBHandler.connect();
            DBHandler.createFullDB(NewConn);
            return NewConn;
        }
        else {
            return DBHandler.connect();
        }
    }

}
