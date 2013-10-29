package codingdojo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GameOfLiveTest {

    @Test
    public void getNextGenerationForMiniWorldCorrectlyEvolvesNextGeneration() {
        /* @formatter:off */
        char[][] generation1 = new char[][] {
                {'#', '#'},
                {'#', '.'}
        };
        char[][] generation2 = new char[][] {
                {'#', '#'},
                {'#', '#'}
        };
        /* @formatter:on */

        GameOfLife world = new GameOfLife(generation1);
        assertArrayEquals(generation2, world.nextGeneration().getPopulation());
    }

    @Test
    public void countLivingNeighborsReturnsTwoForTwoLivingNeighbors() {
        /* @formatter:off */
        char[][] generation1 = new char[][] {
                {'.', '#', '.'},
                {'.', '#', '.'},
                {'.', '#', '.'}
        };
        /* @formatter:on */

        GameOfLife world = new GameOfLife(generation1);
        assertEquals(2, world.countLivingNeighbors(0, 0));
        assertEquals(1, world.countLivingNeighbors(1, 0));
    }

    @Test
    public void countLivingNeighborsReturnsOneForOneLivingNeighbor() {
        /* @formatter:off */
        char[][] generation1 = new char[][] {
                {'.', '#', '.'},
                {'.', '#', '.'},
                {'.', '#', '.'}
        };
        /* @formatter:on */

        GameOfLife world = new GameOfLife(generation1);
        assertEquals(1, world.countLivingNeighbors(1, 0));
    }

    @Test
    public void countLivingNeighborsReturnsThreeForThreeLivingNeighbors() {
        /* @formatter:off */
        char[][] generation1 = new char[][] {
                {'.', '#', '.'},
                {'.', '#', '.'},
                {'.', '#', '.'}
        };
        /* @formatter:on */

        GameOfLife world = new GameOfLife(generation1);
        assertEquals(3, world.countLivingNeighbors(0, 1));
    }

    @Test
    public void isAliveReturnsTrueForLivingCell() {
        char[][] generation1 = new char[][] { { GameOfLife.LIVING_CELL } };

        GameOfLife world = new GameOfLife(generation1);
        assertThat(world.isAlive(0, 0), is(true));
    }

    @Test
    public void isAliveReturnsFalseForDeadCell() {
        char[][] generation1 = new char[][] { { GameOfLife.DEAD_CELL } };

        GameOfLife world = new GameOfLife(generation1);
        assertThat(world.isAlive(0, 0), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenXIsTooSmall() {
        char[][] generation1 = new char[][] { { GameOfLife.LIVING_CELL } };

        GameOfLife world = new GameOfLife(generation1);
        assertThat(world.isAlive(-1, 0), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenXIsTooBig() {
        char[][] generation1 = new char[][] { { GameOfLife.LIVING_CELL } };

        GameOfLife world = new GameOfLife(generation1);
        assertThat(world.isAlive(1, 0), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenYIsTooSmall() {
        char[][] generation1 = new char[][] { { GameOfLife.LIVING_CELL } };

        GameOfLife world = new GameOfLife(generation1);
        assertThat(world.isAlive(0, -1), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenYIsTooBig() {
        char[][] generation1 = new char[][] { { GameOfLife.LIVING_CELL } };

        GameOfLife world = new GameOfLife(generation1);
        assertThat(world.isAlive(0, 1), is(false));
    }
}
