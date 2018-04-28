package turingmachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author 150023118
 */
public class PalindromeTest {
    TuringMachine tm;

    @Before
    public void setUp() {
        File file = new File("./palindrome.txt");
        tm = new TuringMachine(file);
    }

    @Test
    public void testValidInput1() {
        assertTrue(tm.accepts("010"));
    }

    @Test
    public void testValidInput2() {
        assertTrue(tm.accepts("2011102"));
    }

    @Test
    public void testValidInput3() {
        assertTrue(tm.accepts("111"));
    }

    @Test
    public void testValidInput4() {
        assertTrue(tm.accepts("1"));
    }

    @Test
    public void testValidInput5() {
        assertTrue(tm.accepts("12000021"));
    }

    @Test
    public void testInvalidInput1() {
        assertFalse(tm.accepts("001"));
    }

    @Test
    public void testInvalidInput2() {
        assertFalse(tm.accepts("20"));
    }

    @Test
    public void testInvalidInput3() {
        assertFalse(tm.accepts("20110"));
    }

    @Test
    public void testNotInAlphabet() {
        assertFalse(tm.accepts("c"));
    }
}
