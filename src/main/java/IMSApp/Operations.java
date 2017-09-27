package IMSApp;
import java.io.*;
import java.sql.Connection;


public class Operations {

    public static void main(String[] args) throws java.io.IOException{
        firstRun();

    }
    public static void firstRun(Connection c) throws java.io.IOException {
        File statusFile = new File("src/main/statusFileFolder/statusFile.txt"); //TODO location
        boolean firstRun = false;
        if(!statusFile.exists()){
            firstRun = true;
            statusFile.createNewFile();
            System.out.println("file created");
        }
        if(firstRun){
            System.out.println("first run");
            DBHandler.createFullDB(c);
        }
        else {
            System.out.println("not first");
        }
    }

}
