package IMSApp;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Operations {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {

    }

    public static void firstRun() {
        File statusFile = new File("src/main/statusFileFolder/statusFile.txt");
        if(!statusFile.exists()){
            try {
                statusFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DBHandler.createFullDB();
        }
    }

    public static Boolean blankChecker(String[] values){
        Boolean checked = true;
        for (String value : values) {
            if (value == null) {
                checked = false;
                break;
            }
        }
        return checked;
    }

    public static String scrubDate(Date date){
        return dateFormat.format(date);
    }

}
