package IMSApp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Inventory Management System");
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);

        Button iMadeSomething = new Button();
        iMadeSomething.setText("I Made Something");

        Button iSoldSomething = new Button();
        iSoldSomething.setText("I Sold Something");

        Button viewRecords = new Button();
        viewRecords.setText("View/Print Records");

        Button updaterecords = new Button();
        updaterecords.setText("Update a record");

        gp.add(iMadeSomething, 0, 0);
        gp.add(iSoldSomething, 1, 0);
        gp.add(viewRecords, 2, 0);
        gp.add(updaterecords, 3, 0);

        Scene scene = new Scene(gp, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Scene creation(){return null;}

    public Scene sales(){return null;}

    public Scene update(){return null;}

    public Scene records(){return null;}
}
