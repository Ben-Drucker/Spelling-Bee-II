package Back_End;

import java.util.ArrayList;
import java.util.Collections;

public class GeneratePuzzle {
    private int extraSize;
    public String req;
    public ArrayList<String> extra = new ArrayList<String>();

    public GeneratePuzzle(String reqq, ArrayList<String> extraa){
        req = reqq;
        extra = extraa;
        this.extraSize = extra.size();
    }

    public GeneratePuzzle(int extraSize){
        this.extraSize = extraSize;
        init();
        req = extra.remove(0);
    }

    private void init(){
        for(int i = 97; i<123; i++){
            String charStr = String.valueOf((char)i);
            extra.add(charStr);
        }

        Collections.shuffle(extra);
        for(int i = 0; i<26-extraSize-1; i++){
            extra.remove(0);
        }
    }
}
