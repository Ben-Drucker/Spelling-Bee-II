package main.Back_End;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Initialize {
    public GeneratePuzzle gP;
    public Puzzle puzzle;
    public String guessedWordsDisplay = "";
    public static File loadFileName;

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
            File fp = Initialize.loadFileName;
            Scanner scanner = null;
            try{
                scanner = new Scanner(fp);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            String req = scanner.nextLine();
            ArrayList<String> extra = new ArrayList<>();
            for(int i = 0; i<6; i++){
                extra.add(scanner.nextLine());
            }
            ArrayList<String> illegals = new ArrayList<>();
            for(int i = 0; i<19; i++){
                illegals.add(scanner.nextLine());
            }
            ArrayList<String> guessedWords = new ArrayList<>();
            String currentWord = scanner.nextLine();
            while(!currentWord.equals("End Guessed Words")){
                guessedWords.add(currentWord);
                guessedWordsDisplay += currentWord + "\n";
                currentWord = scanner.nextLine();
            }
            guessedWordsDisplay = guessedWordsDisplay.stripTrailing();
            ArrayList<String> solution = new ArrayList<>();
            while(!currentWord.equals("End Solution")){
                currentWord = scanner.nextLine();
                if(!currentWord.equals("End Solution")) {
                    solution.add(currentWord);
                }
            }
            int loadLevel = Integer.parseInt(scanner.nextLine());
            int loadScore = Integer.parseInt(scanner.nextLine());
            int loadNumberGuessed = Integer.parseInt(scanner.nextLine());
            puzzle = new Puzzle(req, extra, guessedWords, illegals, solution, loadLevel, loadScore, loadNumberGuessed);
        }
    }
}
