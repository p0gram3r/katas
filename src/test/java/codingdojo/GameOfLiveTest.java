package codingdojo;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GameOfLiveTest {

    /* @formatter:off */
    private static final char[][] POPULATION = new char[][] {
            {'.', '#', '.'},
            {'.', '#', '.'},
            {'.', '#', '.'}
    };
    /* @formatter:on */

    private GameOfLife world;

    @Before
    public void setup() {
        world = new GameOfLife(POPULATION);
    }

    @Test
    public void testDefaultConstructorUsesConstantChars() {
        assertThat(world.getLivingCellChar(), is(GameOfLife.LIVING_CELL));
        assertThat(world.getDeadCellChar(), is(GameOfLife.DEAD_CELL));
    }

    @Test
    public void getLivingCellCharReturnsValuePassedToConstructor() {
        char nonDefaultChar = 'a';

        world = new GameOfLife(POPULATION, nonDefaultChar, GameOfLife.DEAD_CELL);
        assertThat(world.getLivingCellChar(), is(nonDefaultChar));
    }

    @Test
    public void getDeadCellCharReturnsValuePassedToConstructor() {
        char nonDefaultChar = 'a';

        world = new GameOfLife(POPULATION, GameOfLife.LIVING_CELL, nonDefaultChar);
        assertThat(world.getDeadCellChar(), is(nonDefaultChar));
    }

    @Test
    public void nextGenerationCorrectlyEvolvesNextPopulation() {
        /* @formatter:off */
        char[][] expectedPopulation = new char[][] {
            {'.', '.', '.'},
            {'#', '#', '#'},
            {'.', '.', '.'}
        };
        /* @formatter:on */

        assertArrayEquals(expectedPopulation, world.nextGeneration().getPopulation());
    }

    @Test
    public void countLivingNeighborsReturnsTwoForTwoLivingNeighbors() {
        assertThat(world.countLivingNeighbors(0, 0), is(2));
    }

    @Test
    public void countLivingNeighborsReturnsOneForOneLivingNeighbor() {
        assertThat(world.countLivingNeighbors(1, 0), is(1));
    }

    @Test
    public void countLivingNeighborsReturnsThreeForThreeLivingNeighbors() {
        assertThat(world.countLivingNeighbors(0, 1), is(3));
    }

    @Test
    public void isAliveReturnsTrueForLivingCell() {
        assertThat(world.isAlive(1, 1), is(true));
    }

    @Test
    public void isAliveReturnsFalseForDeadCell() {
        assertThat(world.isAlive(0, 0), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenXIsTooSmall() {
        assertThat(world.isAlive(-1, 0), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenXIsTooBig() {
        assertThat(world.isAlive(3, 0), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenYIsTooSmall() {
        assertThat(world.isAlive(0, -1), is(false));
    }

    @Test
    public void isAliveReturnsFalseWhenYIsTooBig() {
        assertThat(world.isAlive(0, 3), is(false));
    }
}
