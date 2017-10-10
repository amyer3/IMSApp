package IMSApp;
import java.io.File;


public class Operations {

    public static void main(String[] args) {

    }
    public static void firstRun() throws java.io.IOException{
        File statusFile = new File("src/main/statusFileFolder/statusFile.txt");
        if(!statusFile.exists()){
            statusFile.createNewFile();
            DBHandler.createFullDB();
        }
    }
    public static Boolean blankChecker(String[] values){
        Boolean checked = true;
        for (String value : values) {
            if (value == null) {
                checked = false;
                break;
            } else {
                continue;
            }
        }
        return checked;
    }

}
