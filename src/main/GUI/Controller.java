package main.GUI;

/**
 * JavaFX imports
 */
import main.Back_End.Initialize;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Java Imports
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    public Initialize init;

    /**
     * Main scene initialization options
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        guessedWords.setText("");
        quitDialog.setVisible(false);
        init = new Initialize(type);
        req = init.puzzle.req;
        nonReq = init.puzzle.extra;
        this.scrs = init.puzzle.getScores();
        if(type.equals("load")){
            numberOfGuessedWords = init.puzzle.loadNumberGuessed;
            guessedWords.setText(init.guessedWordsDisplay+"\n");
            currentLevel = init.puzzle.loadLevel;
            indicatorTri.setLayoutY(130 + currentLevel*30);
            score.setText(String.valueOf(init.puzzle.loadScore));
        }
        optional1.setText(nonReq.get(0).toUpperCase(Locale.ROOT));
        optional2.setText(nonReq.get(1).toUpperCase(Locale.ROOT));
        optional3.setText(nonReq.get(2).toUpperCase(Locale.ROOT));
        optional4.setText(nonReq.get(3).toUpperCase(Locale.ROOT));
        optional5.setText(nonReq.get(4).toUpperCase(Locale.ROOT));
        optional6.setText(nonReq.get(5).toUpperCase(Locale.ROOT));
        required.setText(req.toUpperCase(Locale.ROOT));
        beginner.setText("Beginner: 0");
        goodStart.setText("Good Start: "+scrs.get(1));
        movingUp.setText("Moving Up: "+scrs.get(2));
        good.setText("Good: "+scrs.get(3));
        solid.setText("Solid: "+scrs.get(4));
        nice.setText("Nice: "+scrs.get(5));
        amazing.setText("Amazing: "+scrs.get(6));
        genius.setText("Genius: "+scrs.get(7));
        unbelievable.setText("Unbelievable: "+scrs.get(8));
        ArrayList<KeyCode> relevantKeyCodes = new ArrayList<>(Arrays.asList(KeyCode.A, KeyCode.B));
        mainFrame.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.BACK_SPACE) {
                bspacePress();
            }
            if (e.getCode() == KeyCode.ENTER) {
                try {
                    validateWord();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                this.currentWord.setText("");
            }
        });
        mainFrame.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.BACK_SPACE){
                bspaceExit();
            }
        });
        Button[] buttonArray = new Button[7];
        buttonArray[0] = required;
        buttonArray[1] = optional1;
        buttonArray[2] = optional2;
        buttonArray[3] = optional3;
        buttonArray[4] = optional4;
        buttonArray[5] = optional5;
        buttonArray[6] = optional6;
        bspace.setShape(svg);
        bspace.setScaleX(0.7);
        bspace.setScaleY(0.7);
        bspace.setPrefHeight(34);
        bspace.setPrefWidth(244);
        bspace.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 1.43");
        scrollPane.setContent(guessedWords);
        for(String s : scrs){
            intScrs.add(Integer.parseInt(s));
        }
        backToMMButton.setOnAction(e->{
            GaussianBlur blur = new GaussianBlur(0);
            mainView.setEffect(blur);
            Timeline blurAnimation = new Timeline();
            blurAnimation.setCycleCount(1);
            blurAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(50), new KeyValue (blur.radiusProperty(),20)));
            blurAnimation.play();
            quitDialog.setVisible(true);
        });
        saveGameButton.setOnAction(event -> {
            save();
        });
        cancelDiag.setOnAction(event -> {
            quitDialog.setVisible(false);
            ((GaussianBlur)mainView.getEffect()).setRadius(0);
        });
        saveDiag.setOnAction(event -> {
            boolean saved = save();
            if(saved) {
                quitDialog.setVisible(false);
                ((GaussianBlur) mainView.getEffect()).setRadius(0);
            }
        });
        System.out.println(init.puzzle.solution);
    }

    /**
     * Buttons from FXML
     */
    @FXML AnchorPane mainView;
    @FXML Button saveGameButton;
    @FXML Button backToMMButton;
    @FXML AnchorPane quitDialog;
    @FXML Button cancelDiag;
    @FXML Button okDiag;
    @FXML Button saveDiag;
    @FXML ImageView imageView;
    @FXML Polygon indicatorTri;
    @FXML Text guessedWords;
    @FXML ScrollPane scrollPane;
    @FXML VBox mainFrame;
    @FXML Text beginner;
    @FXML Text goodStart;
    @FXML Text movingUp;
    @FXML Text good;
    @FXML Text solid;
    @FXML Text nice;
    @FXML Text amazing;
    @FXML Text genius;
    @FXML Text unbelievable;
    @FXML Button optional1;
    @FXML Button optional2;
    @FXML Button optional3;
    @FXML Button optional4;
    @FXML Button optional5;
    @FXML Button optional6;
    @FXML Button required;
    @FXML Button bspace;
    @FXML Text score;
    @FXML Text currentWord;

    /**
     * Other Member Variables
     */
    private Stage window;
    private FileChooser fileChooser;
    private ArrayList<String> nonReq;
    private String req;
    public static String type;
    private final SVGPath svg = new SVGPath();
    private final String myStyHov = "-fx-background-color: #ffffbd#ffffbd; -fx-border-color: #999999#999999; -fx-border-width: 2.5";
    private final String myStyReg = "-fx-background-color: #ffff00#ffff00; -fx-border-color: #000000#000000;";
    private final String myStyClk = "-fx-background-color: #ffffbd#ffffbd; -fx-border-color: #999999#999999; -fx-border-width: 5";
    private int numberOfGuessedWords = 0;
    private int currentLevel = 0;
    private ArrayList<String> scrs;
    private ArrayList<Integer> intScrs = new ArrayList<>();
    private Image img = new Image("main/GUI/Bee_2.png");
    {
        String hex = "M 0 0 L 1 0 l 0.5 1 l -0.5 1 l -1 0 l -0.5 -1 l 0.5 -1M 0 0 L 1 0 l 0.5 1 l -0.5 1 l -1 0 l -0.5 -1 l 0.5 -1";
        svg.setContent(hex);
    }


    public void hoverStartReq(){
        String myStyHovReq = "-fx-background-color: #272727; -fx-border-color: #ffff00#ffff00; -fx-border-width: 2.5";
        required.setStyle(myStyHovReq);
        required.setShape(svg);
    }
    public void hoverStartO1(){
        optional1.setStyle(myStyHov);
        optional1.setShape(svg);
    }
    public void hoverStartO2(){
        optional2.setStyle(myStyHov);
        optional2.setShape(svg);
    }
    public void hoverStartO3(){
        optional3.setStyle(myStyHov);
        optional3.setShape(svg);
    }
    public void hoverStartO4(){
        optional4.setStyle(myStyHov);
        optional4.setShape(svg);
    }
    public void hoverStartO5(){
        optional5.setStyle(myStyHov);
        optional5.setShape(svg);
    }
    public void hoverStartO6(){
        optional6.setStyle(myStyHov);
        optional6.setShape(svg);
    }

    public void mouseClickReq(){
        String myStyClkReq = "-fx-background-color: #272727; -fx-border-color: #ffff00#ffff00; -fx-border-width: 5";
        required.setStyle(myStyClkReq);
        String letterToAdd = required.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }
    public void mouseClickO1(){
        optional1.setStyle(myStyClk);
        String letterToAdd = optional1.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }
    public void mouseClickO2(){
        optional2.setStyle(myStyClk);
        String letterToAdd = optional2.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }
    public void mouseClickO3(){
        optional3.setStyle(myStyClk);
        String letterToAdd = optional3.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }
    public void mouseClickO4(){
        optional4.setStyle(myStyClk);
        String letterToAdd = optional4.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }
    public void mouseClickO5(){
        optional5.setStyle(myStyClk);
        String letterToAdd = optional5.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }
    public void mouseClickO6(){
        optional6.setStyle(myStyClk);
        String letterToAdd = optional6.getText();
        if(tooLong(18)){
            return;
        }
        currentWord.setText(currentWord.getText()+letterToAdd);
    }

    public void hoverStopReq(){
        String myStyRegReq = "-fx-background-color: #000000; -fx-border-color: #ffff00#ffff00;";
        required.setStyle(myStyRegReq);
    }
    public void hoverStopO1(){
        optional1.setStyle(myStyReg);
    }
    public void hoverStopO2(){
        optional2.setStyle(myStyReg);
    }
    public void hoverStopO3(){
        optional3.setStyle(myStyReg);
    }
    public void hoverStopO4(){
        optional4.setStyle(myStyReg);
    }
    public void hoverStopO5(){
        optional5.setStyle(myStyReg);
    }
    public void hoverStopO6(){
        optional6.setStyle(myStyReg);
    }

    public void updateScore(int add){
        String current = score.getText();
        int scoreVal = Integer.parseInt(current);
        scoreVal += add;
        String newScore = String.valueOf(scoreVal);
        score.setText(newScore);

    }

    public void bspaceEnter(){
        bspace.setShape(svg);
        bspace.setScaleX(0.7);
        bspace.setScaleY(0.7);
        bspace.setPrefHeight(34);
        bspace.setPrefWidth(244);
        bspace.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #999999; -fx-border-width: 3.6");

    }

    public void bspaceExit(){
        bspace.setShape(svg);
        bspace.setScaleX(0.7);
        bspace.setScaleY(0.7);
        bspace.setPrefHeight(34);
        bspace.setPrefWidth(244);
        bspace.setStyle("-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-border-width: 1.43");
    }

    public void bspacePress(){
        bspace.setShape(svg);
        bspace.setScaleX(0.7);
        bspace.setScaleY(0.7);
        bspace.setPrefHeight(34);
        bspace.setPrefWidth(244);
        bspace.setStyle("-fx-background-color: #f5f5f5; -fx-border-color: #999999; -fx-border-width: 7.14");
        String cW = currentWord.getText();
        if(cW.length() != 0) {
            String newCW = cW.substring(0, cW.length() - 1);
            currentWord.setText(newCW);
        }
    }

    public void validateWord() throws InterruptedException {
        String word = currentWord.getText().toLowerCase(Locale.ROOT);
        String correctWord = "";
        try {
            correctWord = init.puzzle.GuessWord(word);
            numberOfGuessedWords++;
            updateScore(correctWord.length());
            if(numberOfGuessedWords>1){
                guessedWords.setText(guessedWords.getText()+"\n");
            }
            guessedWords.setText(guessedWords.getText().concat(numberOfGuessedWords + ". " + correctWord));
            int previousLevel = currentLevel;
            updateLevel();
            currentWord.setText("");
            if (previousLevel != currentLevel) {
                int increase = currentLevel - previousLevel;
                beeAnimation(increase);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean tooLong(int maxLength){
        if(currentWord.getText().length() > maxLength){
            System.out.println("Word too long.");
            return true;
        }
        return false;
    }

    private void updateLevel() {
        int currentScore = Integer.parseInt(score.getText());
        for(int level = currentLevel; level < 9; level++){
            if(currentScore < this.intScrs.get(level)){
                this.currentLevel = level - 1;
                break;
            }
            else if(currentScore == this.intScrs.get(8)){
                this.currentLevel = 8;
                break;
            }
        }
    }

    private void beeAnimation(int increase){
        TranslateTransition trans = new TranslateTransition();
        trans.setNode(indicatorTri);
        trans.setDuration(Duration.millis(250));
        trans.setByY(30*increase);
        trans.play();
        double xPos = indicatorTri.getLayoutX();
        double yPos = indicatorTri.getLayoutY();
        imageView.setOpacity(0);
        imageView.setImage(img);
        imageView.setScaleX(1);
        imageView.setScaleY(1);
        imageView.setLayoutX(xPos-60);
        imageView.setLayoutY(yPos-2+30*currentLevel);
        ScaleTransition scale = new ScaleTransition();
        scale.setDuration(Duration.millis(500));
        scale.setDelay(Duration.millis(500));
        scale.setNode(imageView);
        scale.setByX(5);
        scale.setByY(5);
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(500));
        fade.setDelay(Duration.millis(500));
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setNode(imageView);
        scale.play();
        fade.play();
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }

    private boolean save(){
        Date date = new Date();
//        date.toInstant();
//        Calendar.get(Calendar.DAY_OF_MONTH);
        String nonReqDisplay = "";
        for(String letter : nonReq){
            nonReqDisplay += " "+letter;
        }
        this.mainView.setEffect(new GaussianBlur(20));
        this.window = (Stage) saveGameButton.getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("\uD83D\uDCBE Choose Where to Save your Game");
        fileChooser.setInitialFileName("SBII game—"+req+nonReqDisplay+" ("+date+").spell2");
        File fp = fileChooser.showSaveDialog(window);
        if (fp != null) {
            try {
                FileWriter fW = new FileWriter(fp);
                fW.write(req+"\n");
                for(String letter : nonReq){
                    fW.write(letter+"\n");
                }
                for(String letter : init.puzzle.illegals){
                    fW.write(letter+"\n");
                }
                fW.write(guessedWords.getText()+"\n");
                fW.write("End Guessed Words\n");
                for(String word : init.puzzle.solution){
                    fW.write(word+"\n");
                }
                fW.write("End Solution");
                fW.write("\n"+String.valueOf(currentLevel));
                fW.write("\n"+String.valueOf(score.getText()));
                fW.write("\n"+String.valueOf(numberOfGuessedWords));
                fW.close();
                ((GaussianBlur) this.mainView.getEffect()).setRadius(0);
                return true;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        ((GaussianBlur) this.mainView.getEffect()).setRadius(0);
        return false;
    }
}

