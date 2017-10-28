package IMSApp;

import java.sql.SQLException;

public class App {
    public static void main( String[] args ) throws SQLException, java.lang.InterruptedException {
        sGUI gui = new sGUI();
        Ops.firstRun();
        gui.doGui();

    }
}
