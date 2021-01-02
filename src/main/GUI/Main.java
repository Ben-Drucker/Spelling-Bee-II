package main.GUI;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class Main extends Application{

    public static Scene splashScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root2 = FXMLLoader.load(getClass().getResource("InitialGUI.fxml"));
        primaryStage.setTitle("Spelling Bee II 🐝");
        Scene splashScene = new Scene(root2, 600, 375);
        primaryStage.setScene(splashScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
