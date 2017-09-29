package IMSApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws java.sql.SQLException, IOException {
        primaryStage.setTitle("Inventory Management System");
        Parent root = FXMLLoader.load(getClass().getResource("src/main/fxml/splash.fxml"));

        Button iMadeSomething = new Button();
        iMadeSomething.setText("I Made Something");
        iMadeSomething.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                Stage stage = new Stage();
                stage.show();
            }
        } );

        Scene scene = new Scene(root, 800, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void creation(){
        Stage stage = new Stage();
        stage.show();
    }

    private void sales(Connection c){Stage stage = new Stage();}

    private void update(Connection c){Stage stage = new Stage();}

    private void records(Connection c){Stage stage = new Stage();}
}
