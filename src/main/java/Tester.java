import java.nio.file.Files;
import java.nio.file.Paths;

public class Tester {
    public static void main(String[] args) {
        deleteDBRecords();
    }
    private static void deleteDBRecords(){
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
    }
}
