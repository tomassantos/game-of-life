package com.tomas.gof;

import java.util.ArrayList;
import java.util.List;

public class BasicRule implements Rule {

    /**
     * Universe will be change according to the following rules:
     * 1. Any live cell with fewer than two live neighbors dies, as if caused by under population.
     * 2. Any live cell with two or three live neighbors lives on to the next generation.
     * 3. Any live cell with more than three live neighbors dies, as if by overpopulation.
     * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
     *
     * @param universe - that rule will be applied.
     */
    @Override
    public void apply(Universe universe) {
        List<Coordinate> died = new ArrayList<>();
        List<Coordinate> reborn = new ArrayList<>();

        List<Coordinate> coordinates = universe.getCoordinates();
        for (Coordinate coordinate : coordinates) {
            int liveNeighborCount = universe.getLiveNeighborCount(coordinate);
            boolean live = universe.isLive(coordinate);
            boolean dead = !live;

            if (dead && liveNeighborCount == 3) reborn.add(coordinate);
            else if (live && liveNeighborCount < 2) died.add(coordinate);
            else if (live && liveNeighborCount > 3) died.add(coordinate);
        }

        for (Coordinate coordinate : died) {
            universe.setDead(coordinate);
        }

        for (Coordinate coordinate : reborn) {
            universe.setLive(coordinate);
        }
    }
}
