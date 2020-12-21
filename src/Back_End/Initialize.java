package Back_End;

public class Initialize {
    public GeneratePuzzle gP;
    public Puzzle puzzle;
    public Initialize(){
        gP = new GeneratePuzzle(6);
        puzzle = new Puzzle(gP.req, gP.extra, "testFile");
    }
}
