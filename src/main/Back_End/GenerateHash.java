package main.Back_End;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;


public class GenerateHash {

    public HashMap<String, ArrayList<String>> dictionary = new HashMap<>();
    String filename;
    String[] alphLetters = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
            "s", "t", "u", "v", "w", "x", "y", "z" };
    {
        for (int i = 0; i < 26; i++) {
            dictionary.put(alphLetters[i], new ArrayList<>());
        }
    }

    public GenerateHash(String filename) {
        this.filename = filename;
        File checkFile = new File(
                "src/main.Back_End/data/HashData-" + filename + ".ser");
        boolean exists = checkFile.exists();
        if (exists) {
            try{
                FileInputStream fi;
                ObjectInputStream oi;
                fi = new FileInputStream("src/main.Back_End/data/HashData-" + filename + ".ser");
                oi = new ObjectInputStream(fi);
                dictionary = (HashMap<String, ArrayList<String>>) oi.readObject();
                oi.close();
            } catch (FileNotFoundException e) {
            System.out.println("File not found");
            } catch (IOException e) {
                System.out.println("Error initializing stream");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            buildUpHash();
            saveData();
        }
    }
    
    private void buildUpHash(){
        try {
            File fp = new File("src/main.Back_End/data/english3.txt");
			Scanner scanner = new Scanner(fp);
			while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                for(int i = 0; i<26; i++){
                    if(word.contains(alphLetters[i])){
                        dictionary.get(alphLetters[i]).add(word);
                    }
                }
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void saveData(){
        try{
            FileOutputStream f = new FileOutputStream("src/main.Back_End/data/HashData-"+filename+".ser");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(dictionary);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }
    }
}
