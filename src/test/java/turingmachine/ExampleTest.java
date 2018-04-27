package turingmachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author 150023118
 */
public class ExampleTest {
    TuringMachine tm;

    @Before
    public void setUp() {
        File file = new File("./example.txt");
        tm = new TuringMachine(file);
    }

    @Test
    public void testValidInput1() {
        assertTrue(tm.accepts("aabb"));
    }

    @Test
    public void testValidInput2() {
        assertTrue(tm.accepts("a"));
    }

    @Test
    public void testValidInput3() {
        assertTrue(tm.accepts("aaaaaa"));
    }

    @Test
    public void testValidInput4() {
        assertTrue(tm.accepts("bbbb"));
    }

    @Test
    public void testValidInput5() {
        assertTrue(tm.accepts("aaabb"));
    }

    @Test
    public void testInvalidInput1() {
        assertFalse(tm.accepts("bba"));
    }

    @Test
    public void testInvalidInput2() {
        assertFalse(tm.accepts("baba"));
    }

    @Test
    public void testInvalidInput3() {
        assertFalse(tm.accepts("abba"));
    }

    @Test
    public void testInvalidInput4() {
        assertFalse(tm.accepts("abab"));
    }

    @Test
    public void testNotInAlphabet() {
        assertFalse(tm.accepts("c"));
    }

}
