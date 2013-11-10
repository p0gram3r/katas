package codingdojo;

import org.junit.Test;

public class OscillatorsTest extends AbstractPopulationEvolutionTest {

    @Test
    public void testBlinker() {
        /* @formatter:off */
        char[][] BLINKER_1 = new char[][] {
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '#', '#', '#', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.'}
        };
        char[][] BLINKER_2 = new char[][] {
            {'.', '.', '.', '.', '.'},
            {'.', '.', '#', '.', '.'},
            {'.', '.', '#', '.', '.'},
            {'.', '.', '#', '.', '.'},
            {'.', '.', '.', '.', '.'}
        };
        /* @formatter:on */

        assertPopulationEvolution(BLINKER_1, BLINKER_2);
    }

    @Test
    public void testToad() {
        /* @formatter:off */
        char[][] TOAD_1 = new char[][] {
            {'.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '#', '.', '.'},
            {'.', '#', '.', '.', '#', '.'},
            {'.', '#', '.', '.', '#', '.'},
            {'.', '.', '#', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.'}
        };
        char[][] TOAD_2 = new char[][] {
            {'.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.'},
            {'.', '.', '#', '#', '#', '.'},
            {'.', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.'}
        };
        /* @formatter:on */

        assertPopulationEvolution(TOAD_1, TOAD_2);
    }
}
