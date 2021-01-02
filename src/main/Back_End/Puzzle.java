package main.Back_End;

import java.util.ArrayList;
import java.util.HashMap;


public class Puzzle {
    public String req;
    public ArrayList<String> extra;
    public ArrayList<String> guessedWords;
    public ArrayList<String> illegals;
    public ArrayList<String> solution;
    public HashMap<String, ArrayList<String>> dictionary;
    public int loadLevel;
    public int loadScore;
    public int loadNumberGuessed;

    public Puzzle(String req, ArrayList<String> extra, ArrayList<String> guessedWords, ArrayList<String> illegals, ArrayList<String> solution, int loadLevel, int loadScore, int loadNumberGuessed){
        this.req = req;
        this.extra = extra;
        this.guessedWords = guessedWords;
        this.illegals = illegals;
        this.solution = solution;
        this.loadLevel = loadLevel;
        this.loadScore = loadScore;
        this.loadNumberGuessed = loadNumberGuessed;
    }

    public Puzzle(String required, ArrayList<String> extra, String fileName){
        guessedWords = new ArrayList<>();
        TimeElapsed t1 = new TimeElapsed();
        t1.start();
            GenerateHash gH = new GenerateHash(fileName);
            dictionary = gH.dictionary;
        req = required;
        this.extra = extra;
        TimeElapsed t2 = new TimeElapsed();
            illegals = generateIllegals(extra);
            solution = getSolution();
    }

    public String GuessWord(String guess){
        //invalid if word uses illegal letters
        for (String illegal : illegals) {
            if (guess.contains(illegal)) {
                throw new RuntimeException("Illegal Letters");
            }
        }
            
        //invalid if already guessed
        if(guessedWords.contains(guess)){
            throw new RuntimeException("Already Guessed");
        }

        //invalid if word does not use required letter
        if(!guess.contains(req)){
            throw new RuntimeException("Does not contain required letter");
        }

        //invalid if word is not in dictionary
        if(!solution.contains(guess)){
            throw new RuntimeException("Word not in dictionary/solution");
        }
        goodWord(guess);
        return guess;
    }

    private void goodWord(String word){
        guessedWords.add(word);
    }

    public ArrayList<String> generateIllegals(ArrayList<String> extra){
        ArrayList<String> returnString = new ArrayList<>(27 - extra.size());
        for(int i = 97; i<123; i++){
            String checkCharString = String.valueOf((char)i);
            if(!extra.contains(checkCharString)){
                returnString.add(checkCharString);
            }
        }
        returnString.remove(req);
        return returnString;
    }

    private ArrayList<String> getSolution(){
        boolean Bad;
        ArrayList<String> solutionLs = new ArrayList<>();
        ArrayList<String> wordLs = this.dictionary.get(req);
        for (String word : wordLs) {
            Bad = false;
            for (int charPos = 0; charPos<word.length();charPos++) {
                String charCheck = String.valueOf(word.charAt(charPos));
                if(illegals.contains(charCheck)){
                    Bad = true;
                    break;
                }
            }
            if(!Bad && word.contains(req)){
                solutionLs.add(word);
            }
        }

        return solutionLs;
    }

    public ArrayList<String> getScores(){
        int numberChars = 0;
        for (String s : solution) {
            numberChars += s.length();
        }
        ArrayList<String> returnLs = new ArrayList<>();
        int genius = (int)(numberChars*0.6);
        for(int i = 0; i<8; i++){
            String appendStr = String.valueOf((int)(((double)i/7)*(genius)));
            returnLs.add(appendStr);
        }
        returnLs.add(String.valueOf(numberChars));
        return returnLs;
    }

}
