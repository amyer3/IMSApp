package IMSApp;
import java.io.*;
import java.sql.Connection;


public class Operations {

    public static void main(String[] args) throws java.io.IOException{
        Connection c = firstRun();

    }
    public static Connection firstRun() throws java.io.IOException {
        File statusFile = new File("src/main/statusFileFolder/statusFile.txt"); //TODO location
        boolean firstRun = false;
        if(!statusFile.exists()){
            statusFile.createNewFile();
            Connection NewConn = DBHandler.connect();
            DBHandler.createFullDB(NewConn);
            return NewConn;
        }
        else {
            System.out.println("not first"); //TODO delete
            return DBHandler.connect();
        }
    }

}
