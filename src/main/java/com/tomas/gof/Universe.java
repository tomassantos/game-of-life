package com.tomas.gof;

import java.util.*;

public class Universe {
    private final int size;
    private final Cell[][] board;
    private final Set<Coordinate> coordinates;

    /**
     *  Use to construct a universe of n x n size.
     *  All cells in this universe are dead to start.
     *
     * @param size of the universe
     */
    public Universe(int size) {
        this.size = size;
        this.board = new Cell[size][size];
        this.coordinates = new LinkedHashSet<>();

        setCoordinates();
        setCell();
    }

    /**
     * Use to construct a universe of n x n size.
     * Initial state of the universe is randomize, having
     * a mix of live and dead cells.
     *
     * @param size of the universe
     * @param seed to randomize universe cell state
     */
    public Universe(int size, int seed) {
        this(size);
        randomize(seed);
    }

    /**
     * create board coordinate
     */
    private void setCoordinates() {
        for (int x = 0; x < this.size; x++) {
            for (int y = 0; y < this.size; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                this.coordinates.add(coordinate);
            }
        }
    }

    /**
     * create dead cell and add it to the board
     */
    private void setCell() {
        for (Coordinate coordinate : this.coordinates) {
            int x = coordinate.x;
            int y = coordinate.y;

            Cell cell = new Cell();
            cell.setDead();

            board[x][y] = cell;
        }
    }

    /**
     * use to randomize universe cell state
     * @param seed to randomize
     */
    private void randomize(int seed) {
        Cell.State[] states = Cell.State.values();
        Random random = new Random(seed);

        for (Coordinate coordinate : this.coordinates) {
            int x = coordinate.x;
            int y = coordinate.y;

            int pseudorandom = random.nextInt(2);
            Cell cell = board[x][y];
            Cell.State state = states[pseudorandom];
            cell.setState(state);
        }
    }

    /**
     * @return list of all cell coordinates in the universe
     */
    public List<Coordinate> getCoordinates() {
        return new ArrayList<>(coordinates);
    }

    /**
     * @param coordinate of cell
     * @return true if cell is alive
     */
    public boolean isLive(Coordinate coordinate) {
        Cell cell = getCell(coordinate);
        return cell.isLive();
    }

    /**
     * @param coordinate of cell
     * @return true if cell is dead
     */
    public boolean isDead(Coordinate coordinate) {
        Cell cell = getCell(coordinate);
        return cell.isDead();
    }

    /**
     * Change state of cell to dead.
     * @param coordinate of cell
     */
    public void setDead(Coordinate coordinate) {
        Cell cell = getCell(coordinate);
        cell.setDead();
    }

    /**
     * Change state of cell to dead.
     * @param x coordinate of cell
     * @param y coordinate of cell
     */
    public void setDead(int x, int y) {
        Coordinate coordinate = Coordinate.valueOf(x, y);
        setDead(coordinate);
    }

    /**
     * Change state of cell to live.
     * @param coordinate of cell
     */
    public void setLive(Coordinate coordinate) {
        Cell cell = getCell(coordinate);
        cell.setLive();
    }

    /**
     * Change state of cell to live.
     * @param x coordinate of cell
     * @param y coordinate of cell
     */
    public void setLive(int x, int y) {
        Coordinate coordinate = Coordinate.valueOf(x, y);
        setLive(coordinate);
    }

    /**
     * @param coordinate of cell
     * @return number of live neighbor cell
     */
    public int getLiveNeighborCount(Coordinate coordinate) {
        List<Coordinate> neighbors = getLiveNeighbors(coordinate);
        return neighbors.size();
    }

    /**
     * @param coordinate of cell
     * @return list of coordinates of live neighbor cells
     */
    public List<Coordinate> getLiveNeighbors(Coordinate coordinate) {
        List<Coordinate> liveNeighbors = new ArrayList<>();

        List<Coordinate> neighbors = getNeighbors(coordinate);
        for (Coordinate neighbor : neighbors) {
            Cell cell = getCell(neighbor);
            boolean live = cell.isLive();
            if (live) liveNeighbors.add(neighbor);
        }

        return liveNeighbors;
    }

    /**
     * All cells have eight neighbors. Neighbors are to the north,
     * north east, east, south east, south, south west, west and
     * north west of a cell.
     *
     * Cell with neighbors outside the bounds of the universe will get
     * its neighbor from the opposite side of the universe.
     *
     * For example:
     * Given a universe of size 3 and a cell with a coordinate of (0, 0)
     * Then the north neighbor would be the cell at coordinate (2, 0) instead of (-1, 0)
     * And the north west neighbor would be the cell at coordinate (2, 2) instead of (-1, -1)
     *
     *
     * @param coordinate of cell
     * @return list of coordinates all  neighbor cells
     */
    public List<Coordinate> getNeighbors(Coordinate coordinate) {
        throwExceptionWhenOutOfBounds(coordinate);

        List<Coordinate> neighbors = new ArrayList<>();
        neighbors.add(coordinate.add(- 1, - 1));
        neighbors.add(coordinate.add(- 1, - 0));
        neighbors.add(coordinate.add(- 1, + 1));
        neighbors.add(coordinate.add(+ 0, - 1));
        neighbors.add(coordinate.add(+ 0, + 1));
        neighbors.add(coordinate.add(+ 1, - 1));
        neighbors.add(coordinate.add(+ 1, + 0));
        neighbors.add(coordinate.add(+ 1, + 1));

        //replace neighbor if out of bound
        for (int i = 0; i < neighbors.size(); i++) {
            Coordinate neighbor = neighbors.get(i);

            int x = neighbor.x;
            int y = neighbor.y;

            boolean inBounds = isInBounds(x, y);
            if(inBounds) continue;

            if(x < 0) x = size + x;
            else if( x == size) x = size - x;

            if(y < 0) y = size + y;
            else if( y == size) y = size - y;

            neighbors.set(i, Coordinate.valueOf(x, y));
        }
        return neighbors;
    }

    private void throwExceptionWhenOutOfBounds(Coordinate coordinate) {
        if(isOutOfBounds(coordinate)) throw new IllegalArgumentException("Coordinate: " + coordinate.toString() + " is out of bounds.");
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    private boolean isOutOfBounds(Coordinate coordinate) {
        return isOutOfBounds(coordinate.x, coordinate.y);
    }

    private boolean isOutOfBounds(int x, int y) {
        return !isInBounds(x, y);
    }

    private Cell getCell(Coordinate coordinate) {
        throwExceptionWhenOutOfBounds(coordinate);
        return board[coordinate.x][coordinate.y];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Cell cell = board[x][y];
                boolean isLive = cell.isLive();
                String state = isLive ? " 1 " : "   ";
                builder.append("|").append(state).append("|");
            }
            builder.append("\n");
        }

        return builder.toString();
    }
}
