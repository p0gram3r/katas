package codingdojo;

public class GameOfLife {

    public static final char LIVING_CELL = '#';
    public static final char DEAD_CELL = '.';

    private char[][] generation;

    public GameOfLife(char[][] generation) {
        // TODO boundary check!
        this.generation = generation;
    }

    // Conway's Game of Life
    // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
    // Any live cell with two or three live neighbours lives on to the next generation.
    // Any live cell with more than three live neighbours dies, as if by overcrowding.
    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

    public char[][] getNextGeneration() {
        char[][] newGeneration = prepareNexGeneration();

        for (int y = 0; y < generation.length; y++) {
            for (int x = 0; x < generation[y].length; x++) {
                int livingNeighbors = countLivingNeighbors(x, y);

                newGeneration[y][x] = generation[y][x];

                if (isAlive(x, y)) {
                    if (livingNeighbors < 2 || livingNeighbors > 3) {
                        newGeneration[y][x] = DEAD_CELL;
                    } else {
                        newGeneration[y][x] = LIVING_CELL;
                    }
                } else {
                    if (livingNeighbors == 3) {
                        newGeneration[y][x] = LIVING_CELL;
                    }
                }

            }
        }

        // IMPORTANT: replace world population
        generation = newGeneration;

        return generation;
    }

    public char[][] prepareNexGeneration() {
        char[][] clone = new char[generation.length][];
        for (int i = 0; i < generation.length; i++) {
            clone[i] = new char[generation[0].length];
        }
        return clone;
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
        return x >= 0 && x < generation[0].length && y >= 0 && y < generation.length && generation[y][x] == LIVING_CELL;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < generation.length; y++) {
            for (int x = 0; x < generation[y].length; x++) {
                sb.append(generation[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
