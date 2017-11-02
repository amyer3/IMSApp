package IMSApp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class Tester {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("1- Delete DB Records | 2- Add 10 Records");
        int i = input.nextInt();
        if(i == 1){
            System.out.println("OK!");
            deleteDBRecords();
        } else if (i == 2){
            System.out.println("OK!");
            addRecords();
        }
    }
    static void deleteDBRecords(){
        try {
            Files.deleteIfExists(Paths.get("/Users/Alex/IMSApp/maindb.log"));
            Files.deleteIfExists(Paths.get("/Users/Alex/IMSApp/maindb.properties"));
            Files.deleteIfExists(Paths.get("/Users/Alex/IMSApp/maindb.log"));
            Files.deleteIfExists(Paths.get("/Users/Alex/IMSApp/maindb.script"));
            Files.deleteIfExists(Paths.get("/Users/Alex/IMSApp/src/main/statusFileFolder/statusFile.txt"));
            Files.deleteIfExists(Paths.get("/Users/Alex/IMSApp/maindb.tmp"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    static void addRecords(){
        Random random = new Random();
        for (int i = 0; i < 10 ; i++) {
            int id = random.nextInt(10000);
            int Cogs = random.nextInt(50);
            String desc = "Auto Generated Test Unit "+ i;
            String dateMade = Ops.scrubDate(new Date());
            try {
                DBHandler.addRec(Integer.toString(id), desc, Integer.toString(Cogs), dateMade);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("completed");
    }
}
