package codingdojo;

public class GameOfLife {

    public static final char LIVING_CELL = '#';
    public static final char DEAD_CELL = '.';

    private char[][] generation;

    public GameOfLife(char[][] generation) {
        // TODO boundary check!
        this.generation = generation;
    }

    public char[][] getNextGeneration() {
        return generation;
    }

    public int getLivingNeighbors(int x, int y) {

        return 0;
    }

    // FIXME bad design!
    public boolean isAlive(int x, int y) {
        return x >= 0 && x < generation[0].length && y >= 0 && y < generation.length && generation[x][y] == LIVING_CELL;
    }
}
