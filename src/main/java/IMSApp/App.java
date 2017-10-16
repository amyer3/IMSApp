package IMSApp;

import java.sql.SQLException;

public class App {
    public static void main( String[] args ) throws SQLException, java.lang.InterruptedException {
        sGUI gui = new sGUI();
        Operations.firstRun();
        gui.doGui();

    }
}
