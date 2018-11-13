package turingmachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import exceptions.InvalidDescriptionException;
import factory.TuringFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit tests using the example Turing machine description provided.
 *
 * @author Ryosuke Minami
 */
public class TMSyntaxTest {
    private TuringMachine tm;
    TuringFactory factory;

    @Before
    public void setUp() {
        factory = new TuringFactory();
    }

    @Test
    public void testCommentedSpec() throws IOException {
        tm = factory.tmFromFilePath("tms/commented.tm");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testNotInAlphabetLeft() throws IOException {
        tm = factory.tmFromFilePath("tms/invalid1.tm");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testNotInAlphabetRight() throws IOException {
        tm = factory.tmFromFilePath("tms/invalid2.tm");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testInvalidMove() throws IOException {
        tm = factory.tmFromFilePath("tms/invalid3.tm");
    }

    @Test(expected = InvalidDescriptionException.class)
    public void testIncompleteSpec() throws IOException {
        tm = factory.tmFromFilePath("tms/invalid4.tm");
    }
}