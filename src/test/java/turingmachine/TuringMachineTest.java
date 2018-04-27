package turingmachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author 150023118
 */
public class TuringMachineTest {
    TuringMachine tm;

    @Before
    public void setUp() {
        File file = new File("./input.txt");
        tm = new TuringMachine(file);
    }

    @Test
    public void testValidInput() {
        assertTrue(tm.processInput("aabb"));
    }

    @Test
    public void testInvalidInput() {
        assertFalse(tm.processInput("abbb"));
    }

}
