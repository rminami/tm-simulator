package turingmachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author 150023118
 */
public class ExampleTuringMachineTest {
    TuringMachine tm;

    @Before
    public void setUp() {
        File file = new File("./example.txt");
        tm = new TuringMachine(file);
    }

    @Test
    public void testValidInput() {
        assertTrue(tm.accepts("aabb"));
    }

    @Test
    public void testInvalidInput() {
        assertFalse(tm.accepts("bba"));
    }

    @Test
    public void testNotInAlphabet() {
        assertFalse(tm.accepts("c"));
    }

}
