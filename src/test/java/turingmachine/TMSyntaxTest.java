package turingmachine;

import exceptions.InvalidDescriptionException;
import exceptions.InvalidInputException;
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
    private TuringFactory factory;

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

    @Test(expected = InvalidInputException.class)
    public void testDuplicateRule() throws IOException {
        tm = factory.tmFromFilePath("tms/invalid5.tm");
    }
}