package codingdojo;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class GameOfLiveStillLifesTest {

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
                { BLOCK }, 
                { BEEHIVE }, 
                { BOAT } 
        });
        /* @formatter:on */
    }

    private GameOfLife world;
    private char[][] expectedResult;

    // TODO super class
    public GameOfLiveStillLifesTest(char[][] pattern) {
        this.world = new GameOfLife(pattern);
        this.expectedResult = pattern;
    }

    @Test
    public void getNextGeneration() {
        System.out.println(world.toString());
        System.out.println();

        assertArrayEquals(expectedResult, world.getNextGeneration());

        System.out.println(world.toString());
        System.out.println();
    }

}
