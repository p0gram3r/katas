package codingdojo;

public class GameOfLife {

    public static final char LIVING_CELL = '#';
    public static final char DEAD_CELL = '.';

    private char[][] population;
    private char livingCellChar;
    private char deadCellChar;

    public GameOfLife(char[][] initialPopulation) {
        this(initialPopulation, LIVING_CELL, DEAD_CELL);
    }

    public GameOfLife(char[][] initialPopulation, char livingCellChar, char deadCellChar) {
        this.livingCellChar = livingCellChar;
        this.deadCellChar = deadCellChar;
        this.population = initialPopulation;
    }

    // Conway's Game of Life
    // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
    // Any live cell with two or three live neighbours lives on to the next population.
    // Any live cell with more than three live neighbours dies, as if by overcrowding.
    // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

    public GameOfLife nextGeneration() {

        // prepare the
        char[][] newPopulation = new char[population.length][];
        for (int i = 0; i < population.length; i++) {
            newPopulation[i] = new char[population[0].length];
        }

        for (int y = 0; y < population.length; y++) {
            for (int x = 0; x < population[y].length; x++) {
                int livingNeighbors = countLivingNeighbors(x, y);

                newPopulation[y][x] = population[y][x];

                if (isAlive(x, y)) {
                    if (livingNeighbors < 2 || livingNeighbors > 3) {
                        newPopulation[y][x] = deadCellChar;
                    } else {
                        newPopulation[y][x] = livingCellChar;
                    }
                } else {
                    if (livingNeighbors == 3) {
                        newPopulation[y][x] = livingCellChar;
                    }
                }

            }
        }

        // IMPORTANT: replace world population
        population = newPopulation;

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
        return x >= 0 && x < population[0].length && y >= 0 && y < population.length && population[y][x] == livingCellChar;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < population.length; y++) {
            for (int x = 0; x < population[y].length; x++) {
                sb.append(population[y][x]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public char[][] getPopulation() {
        return population;
    }

    public char getLivingCellChar() {
        return livingCellChar;
    }

    public char getDeadCellChar() {
        return deadCellChar;
    }

}
