package inventory.controller;

import com.sun.java.swing.plaf.windows.WindowsInternalFrameTitlePane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;// for demo

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //System.out.println("javafx.runtime.version: " + System.getProperty("javafx.runtime.version"));
        Parent root = FXMLLoader.load(getClass().getResource("../view/part.fxml"));
        primaryStage.setTitle("Inventory Management System");
//        primaryStage.setMinHeight(420);
//        primaryStage.setMinWidth(996);
        primaryStage.setResizable(false);// for add/modify screens
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
