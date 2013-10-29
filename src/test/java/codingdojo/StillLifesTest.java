package codingdojo;

import org.junit.Test;

public class StillLifesTest extends AbstractPopulationEvolutionTest {

    @Test
    public void testBlock() {
        /* @formatter:off */
        char[][] BLOCK = new char[][] {
            {'.', '.', '.', '.'},
            {'.', '#', '#', '.'},
            {'.', '#', '#', '.'},
            {'.', '.', '.', '.'}
        };

        /* @formatter:on */
        assertPopulationEvolution(BLOCK, BLOCK);
    }

    @Test
    public void testBeeHive() {
        /* @formatter:off */
        char[][] BEEHIVE = new char[][] {
            {'.', '.', '.', '.', '.', '.'},
            {'.', '.', '#', '#', '.', '.'},
            {'.', '#', '.', '.', '#', '.'},
            {'.', '.', '#', '#', '.', '.'},
            {'.', '.', '.', '.', '.', '.'}
        };
        /* @formatter:on */

        assertPopulationEvolution(BEEHIVE, BEEHIVE);
    }

    @Test
    public void testBoat() {
        /* @formatter:off */
       char[][] BOAT = new char[][] {
            {'.', '.', '.', '.', '.'},
            {'.', '#', '#', '.', '.'},
            {'.', '#', '.', '#', '.'},
            {'.', '.', '#', '.', '.'},
            {'.', '.', '.', '.', '.'}
        };
        /* @formatter:on */

        assertPopulationEvolution(BOAT, BOAT);
    }
}
