package Back_End;


public class Main {
    public static void main(String[] args){
        // String req = "g";
        // ArrayList<String> extra = new ArrayList<String>();
        // extra.add("d");
        // extra.add("e");
        // extra.add("i");
        // extra.add("l");
        // extra.add("t");
        // extra.add("u");
        // GeneratePuzzle gP = new GeneratePuzzle(req, extra);
        GeneratePuzzle gP = new GeneratePuzzle(6);
        Puzzle puzzle = new Puzzle(gP.req, gP.extra, "testFile");
        
        System.out.println(puzzle.req);
        System.out.println(puzzle.extra);
        System.out.println(puzzle.solution);
    }
}