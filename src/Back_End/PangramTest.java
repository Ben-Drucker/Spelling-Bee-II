package Back_End;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PangramTest {

    Pangram pG = new Pangram("english3.txt","z");

    @Test
    void getPangrams() {
        pG.getPangrams();
        System.out.println("finished");
    }

    @Test
    void savePangrams() {
    }
}