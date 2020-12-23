package GUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application implements EventHandler<ActionEvent>{

    public static String type;

    Button button = new Button("Click Me!!!");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root2 = FXMLLoader.load(getClass().getResource("initialGUI.fxml"));
        primaryStage.setTitle("Spelling Bee II 🐝");
        Scene splashScene = new Scene(root2, 600, 375);
        primaryStage.setScene(splashScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(actionEvent.getSource()==button){
            System.out.println("I just got clicked!!!!");
        }
    }
}
