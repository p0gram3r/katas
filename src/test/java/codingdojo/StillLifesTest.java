package codingdojo;

import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class StillLifesTest extends AbstractGameOfLifeEvolutionTest {

    /* @formatter:off */
    private static final char[][] BLOCK = new char[][] {
        {'.', '.', '.', '.'},
        {'.', '#', '#', '.'},
        {'.', '#', '#', '.'},
        {'.', '.', '.', '.'}
    };

    private static final char[][] BEEHIVE = new char[][] {
        {'.', '.', '.', '.', '.', '.'},
        {'.', '.', '#', '#', '.', '.'},
        {'.', '#', '.', '.', '#', '.'},
        {'.', '.', '#', '#', '.', '.'},
        {'.', '.', '.', '.', '.', '.'}
    };

    private static final char[][] BOAT = new char[][] {
        {'.', '.', '.', '.', '.'},
        {'.', '#', '#', '.', '.'},
        {'.', '#', '.', '#', '.'},
        {'.', '.', '#', '.', '.'},
        {'.', '.', '.', '.', '.'}
    };
    /* @formatter:on */

    @Parameters
    public static Collection<Object[]> data() {
        /* @formatter:off */
        return Arrays.asList(new Object[][] { 
                { BLOCK}, 
                { BEEHIVE }, 
                { BOAT } 
        });
        /* @formatter:on */
    }

    public StillLifesTest(char[][] seed) {
        super(seed, seed);
    }
}
