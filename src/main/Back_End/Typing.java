package main.Back_End;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;

public class Typing {

    public static void handleTyping(Node node, KeyCode keyCode){
        node.setOnKeyPressed(e -> {
            if(e.getCode() == keyCode){
                keyPressTask();
            }
        });
        node.setOnKeyPressed(e -> {
            if(e.getCode() == keyCode){
                keyReleasedTask();
            }
        });
    }

    private static void keyReleasedTask() {
    }

    private static void keyPressTask() {
    }

}
