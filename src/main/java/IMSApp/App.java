package IMSApp;

import java.io.IOException;
import java.sql.SQLException;

public class App {
    public static void main( String[] args ) throws IOException, SQLException, java.lang.InterruptedException {
        sGUI gui = new sGUI();
        Operations.firstRun();
        gui.doGui();

    }
}
