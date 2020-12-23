package Back_End;

import java.util.ArrayList;
import java.util.HashMap;

public class Pangram {

    ArrayList<String> listOfPangrams = new ArrayList<>();;
    HashMap<String, ArrayList<String>> dictionary;
    String reqLetter;

    public Pangram(String filename, String reqLetter){
        GenerateHash gH = new GenerateHash(filename);
        this.dictionary = gH.dictionary;
        this.reqLetter = reqLetter;
    }

    public void getPangrams(){
        ArrayList<String> searchArray = this.dictionary.get(this.reqLetter);
        for(String Word : searchArray){
            ArrayList<Character> uniqueChars = new ArrayList<>();
            char[] word = Word.toCharArray();
            for(Character letter : word){
                if(!uniqueChars.contains(letter)){
                    uniqueChars.add(letter);
                }
            }
            if(uniqueChars.size() == 7){
                this.listOfPangrams.add(Word);
            }
        }
    }

    public void savePangrams(){
        //TODO: Implement Save
    }

}
