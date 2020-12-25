package Back_End;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitializeTest {

    @Test
    public void testPang(){
        Initialize init = new Initialize("pang");
        System.out.println("done");
    }

    @Test
    public void testSave(){
        Initialize init = new Initialize("load");
        System.out.println("done");
    }

}