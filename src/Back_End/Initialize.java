package Back_End;

import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Initialize {
    public GeneratePuzzle gP;
    public Puzzle puzzle;
    public Initialize(String type){
        if(type.equals("random")) {
            gP = new GeneratePuzzle(6);
            puzzle = new Puzzle(gP.req, gP.extra, "english3.txt");
        }
        else if(type.equals("pang")) {
            ArrayList<String> allLetters = new ArrayList<>(List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
                    "s", "t", "u", "v", "w", "x", "y", "z"));
            Collections.shuffle(allLetters);
            String randomRequired = allLetters.get(0);
            Pangram pG = new Pangram("english3.txt", randomRequired);
            ArrayList<String> pangrams = pG.getPangrams();
            Collections.shuffle(pangrams);
            String randomPangram = pangrams.get(0);
            ArrayList<String> extra = new ArrayList<>();
            for(int i=0; i< randomPangram.length(); i++){
                if(!extra.contains(String.valueOf(randomPangram.charAt(i))) && !String.valueOf(randomPangram.charAt(i)).equals(randomRequired)){
                    extra.add(String.valueOf(randomPangram.charAt(i)));
                }
            }
            Collections.shuffle(extra);
            gP = new GeneratePuzzle(randomRequired, extra);
            puzzle = new Puzzle(gP.req, gP.extra, "english3.txt");
        }
        else if(type.equals("load")){
//            FileChooser fileChooser = new FileChooser();
//            fileChooser.setTitle("\uD83D\uDCBE Choose Where to Open your Saved Game");
//            File fp = fileChooser.showOpenDialog(window);
//            puzzle = new Puzzle();
        }
    }
}
