package codingdojo;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public abstract class AbstractGameOfLifeEvolutionTest {

    protected GameOfLife world;
    protected char[][] expectedPopulation;

    public AbstractGameOfLifeEvolutionTest(char[][] seed, char[][] expectedPopulation) {
        this.world = new GameOfLife(seed);
        this.expectedPopulation = expectedPopulation;
    }

    @Test
    public void getNextGeneration() {
        assertArrayEquals(expectedPopulation, world.nextGeneration().getPopulation());
    }
}
