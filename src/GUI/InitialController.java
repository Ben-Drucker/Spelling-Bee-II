package GUI;

import Back_End.TimeElapsed;
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
                                    gen("random");
                                    return null;
                                }
                            };
                            Thread background = new Thread(task);
                            task.setOnSucceeded(event1 -> {
                                this.window.setScene(mainScene);
                            });
                            background.start();
                        });



                        pang.setOnAction(event -> {
                            prog.setVisible(true);
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    gen("pang");
                                    return null;
                                }
                            };
                            Thread background = new Thread(task);
                            task.setOnSucceeded(event1 -> {
                                this.window.setScene(mainScene);
                            });
                            background.start();
                        });



                        load.setOnAction(event -> {
                            prog.setVisible(true);
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    gen("load");
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

    public void gen(String option){
        this.window = (Stage) random.getScene().getWindow();
        try {
            Controller.type = option;
            TimeElapsed tE = new TimeElapsed();
            this.root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
            tE.pp("mili", "*************");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.mainScene = new Scene(root, 800, 500);    // main scene
    }
}