package GUI;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitialController implements Initializable {

    @FXML VBox v;
    @FXML Button util;
    @FXML Button random;
    @FXML Button pang;
    @FXML Button load;
    @FXML ProgressIndicator prog;
    private Stage window;
    private Parent root = null;
    private Scene mainScene;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(random);
        prog.setVisible(false);
        random.setOnAction(event -> {
            prog.setVisible(true);
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    gen();
                    return null;
                }
            };
            Thread background = new Thread(task);
            task.setOnSucceeded(event1 -> {
                this.window.setScene(mainScene);
            });
            background.start();
        });
    }

    public void gen(){
        this.window = (Stage) random.getScene().getWindow();
        try {
            this.root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mainScene = new Scene(root, 800, 500);    // main scene
    }
}
