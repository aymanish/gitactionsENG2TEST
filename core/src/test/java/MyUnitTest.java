import com.mygdx.game.MyUnit;
import org.junit.Test;
import static org.junit.Assert.*;

//Test to check if CI works:
//Run all tests here as the CI config checks tests on this file currently:
public class MyUnitTest {


    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetw", result);

    }
    
        @Test
    public void testConcatenate2() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("1", "2");

        assertEquals("12", result);

    }
}
