package turingmachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * @author 150023118
 */
public class BinaryAdditionTest {
    TuringMachine tm;

    @Before
    public void setUp() {
        File file = new File("./add.txt");
        tm = new TuringMachine(file);
    }

    @Test
    public void testValidInput1() {
        assertTrue(tm.accepts("0#0#0"));
    }

    @Test
    public void testValidInput2() {
        assertTrue(tm.accepts("01#1#11"));
    }

    @Test
    public void testValidInput3() {
        assertTrue(tm.accepts("00#111#111"));
    }

    @Test
    public void testValidInput4() {
        assertTrue(tm.accepts("10#10#01"));
    }

//    @Test
//    public void testValidInput5() {
//        assertTrue(tm.accepts("12000021"));
//    }

    @Test
    public void testInvalidInput1() {
        assertFalse(tm.accepts("0#0"));
    }

    @Test
    public void testInvalidInput2() {
        assertFalse(tm.accepts("0#0#1"));
    }

    @Test
    public void testInvalidInput3() {
        assertFalse(tm.accepts("000#111#11"));
    }

    @Test
    public void testNotInAlphabet() {
        assertFalse(tm.accepts("c"));
    }
}
