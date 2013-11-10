package codingdojo;

import static org.junit.Assert.assertArrayEquals;

public abstract class AbstractPopulationEvolutionTest {

    protected void assertPopulationEvolution(char[][] initialPopulation, char[][] expectedPopulationOfNextGeneration) {
        GameOfLife world = new GameOfLife(initialPopulation);

        assertArrayEquals(expectedPopulationOfNextGeneration, world.nextGeneration().getPopulation());
    }
}
