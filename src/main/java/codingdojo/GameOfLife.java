package codingdojo;

public class GameOfLife {

    public static final char LIVING_CELL = '#';
    public static final char DEAD_CELL = '.';

    private char[][] currentPopulation;

    public GameOfLife(char[][] generation) {
        // TODO boundary check!
        this.currentPopulation = generation;
    }

    // Conway's Game of Life
    // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
    // Any live cell with two or three live neighbours lives on to the next currentPopulation.
    // Any live cell with more than three live neighbours dies, as if by overcrowding.
    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

    public GameOfLife nextGeneration() {

        // prepare the
        char[][] newPopulation = new char[currentPopulation.length][];
        for (int i = 0; i < currentPopulation.length; i++) {
            newPopulation[i] = new char[currentPopulation[0].length];
        }

        for (int y = 0; y < currentPopulation.length; y++) {
            for (int x = 0; x < currentPopulation[y].length; x++) {
                int livingNeighbors = countLivingNeighbors(x, y);

                newPopulation[y][x] = currentPopulation[y][x];

                if (isAlive(x, y)) {
                    if (livingNeighbors < 2 || livingNeighbors > 3) {
                        newPopulation[y][x] = DEAD_CELL;
                    } else {
                        newPopulation[y][x] = LIVING_CELL;
                    }
                } else {
                    if (livingNeighbors == 3) {
                        newPopulation[y][x] = LIVING_CELL;
                    }
                }

            }
        }

        // IMPORTANT: replace world population
        currentPopulation = newPopulation;

        return this;
    }

    public int countLivingNeighbors(int x, int y) {
        int livingNeigbors = 0;

        livingNeigbors += isAlive(x, y - 1) ? 1 : 0;
        livingNeigbors += isAlive(x + 1, y - 1) ? 1 : 0;
        livingNeigbors += isAlive(x + 1, y) ? 1 : 0;
        livingNeigbors += isAlive(x + 1, y + 1) ? 1 : 0;
        livingNeigbors += isAlive(x, y + 1) ? 1 : 0;
        livingNeigbors += isAlive(x - 1, y + 1) ? 1 : 0;
        livingNeigbors += isAlive(x - 1, y) ? 1 : 0;
        livingNeigbors += isAlive(x - 1, y - 1) ? 1 : 0;

        return livingNeigbors;
    }

    // FIXME bad design!
    public boolean isAlive(int x, int y) {
        // !!! swap y and x as the are wrapping arrays uses y to access all lines !!!
        return x >= 0 && x < currentPopulation[0].length && y >= 0 && y < currentPopulation.length && currentPopulation[y][x] == LIVING_CELL;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < currentPopulation.length; y++) {
            for (int x = 0; x < currentPopulation[y].length; x++) {
                sb.append(currentPopulation[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getPopulation() {
        return currentPopulation;
    }
}
