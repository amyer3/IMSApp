package IMSApp;
import java.io.*;
import java.sql.*;


public class Operations {

    public static void main(String[] args) {

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
    public static Boolean blankChecker(String[] values){
        Boolean checked = true;
        for (int i = 0; i < values.length; i++) {
            if(values[i]== null){
                checked = false;
                break;
            } else {
                continue;
            }
        }
        return checked;
    }

}
