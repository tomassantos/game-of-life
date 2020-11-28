package com.tomas.gof;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.fail;

public class UniverseTest {

    private Universe universe;

    @Before
    public void setUp() {
        universe = new Universe(3, 1);
    }

    @Test
    public void isLive() {
        Coordinate coordinate = Coordinate.valueOf(0, 1);
        boolean live = universe.isLive(coordinate);
        assertThat(live, is(true));
    }

    @Test
    public void isNotLive() {
        Coordinate coordinate = Coordinate.valueOf(0, 0);
        boolean live = universe.isLive(coordinate);
        assertThat(live, is(false));
    }

    @Test
    public void isDead() {
        Coordinate coordinate = Coordinate.valueOf(0, 0);
        boolean dead = universe.isDead(coordinate);
        assertThat(dead, is(true));
    }

    @Test
    public void isNotDead() {
        Coordinate coordinate = Coordinate.valueOf(0, 1);
        boolean dead = universe.isDead(coordinate);
        assertThat(dead, is(false));
    }

    @Test
    public void getNeighbors1() {
        Coordinate coordinate = Coordinate.valueOf(1, 1);
        List<Coordinate> neighbors = universe.getNeighbors(coordinate);

        int numberOfNeighbors = neighbors.size();
        assertThat(numberOfNeighbors, is(8));

        List<Coordinate> expected = new ArrayList<>();
        expected.add(Coordinate.valueOf(0,0));
        expected.add(Coordinate.valueOf(0,1));
        expected.add(Coordinate.valueOf(0,2));
        expected.add(Coordinate.valueOf(1,0));
        expected.add(Coordinate.valueOf(1,2));
        expected.add(Coordinate.valueOf(2,0));
        expected.add(Coordinate.valueOf(2,1));
        expected.add(Coordinate.valueOf(2,2));
        assertThat(neighbors, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void getNeighbors2() {
        Coordinate coordinate = Coordinate.valueOf(0, 0);
        List<Coordinate> neighbors = universe.getNeighbors(coordinate);

        int numberOfNeighbors = neighbors.size();
        assertThat(numberOfNeighbors, is(8));

        List<Coordinate> expected = new ArrayList<>();
        expected.add(Coordinate.valueOf(2,2));
        expected.add(Coordinate.valueOf(2,0));
        expected.add(Coordinate.valueOf(2,1));
        expected.add(Coordinate.valueOf(0,2));
        expected.add(Coordinate.valueOf(0,1));
        expected.add(Coordinate.valueOf(1,2));
        expected.add(Coordinate.valueOf(1,0));
        expected.add(Coordinate.valueOf(1,1));
        assertThat(neighbors, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void getNeighbors3() {
        Coordinate coordinate = Coordinate.valueOf(2, 1);
        List<Coordinate> neighbors = universe.getNeighbors(coordinate);

        int numberOfNeighbors = neighbors.size();
        assertThat(numberOfNeighbors, is(8));

        List<Coordinate> expected = new ArrayList<>();
        expected.add(Coordinate.valueOf(1,0));
        expected.add(Coordinate.valueOf(1,1));
        expected.add(Coordinate.valueOf(1,2));
        expected.add(Coordinate.valueOf(2,0));
        expected.add(Coordinate.valueOf(2,2));
        expected.add(Coordinate.valueOf(0,0));
        expected.add(Coordinate.valueOf(0,1));
        expected.add(Coordinate.valueOf(0,2));
        assertThat(neighbors, containsInAnyOrder(expected.toArray()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getNeighbors4() {
        Coordinate coordinate = Coordinate.valueOf(-1, -1);
        universe.getNeighbors(coordinate);
        fail();
    }

    @Test
    public void setDead() {
        Coordinate coordinate = Coordinate.valueOf(0, 1);
        universe.setDead(coordinate);
        boolean dead = universe.isDead(coordinate);
        assertThat(dead, is(true));
    }

    @Test
    public void setDead2() {
        Coordinate coordinate = Coordinate.valueOf(0, 1);
        universe.setDead(coordinate.x, coordinate.y);
        boolean dead = universe.isDead(coordinate);
        assertThat(dead, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setDead3() {
        Coordinate coordinate = Coordinate.valueOf(-1, -1);
        universe.setDead(coordinate.x, coordinate.y);
        fail();
    }

    @Test
    public void setLive() {
        Coordinate coordinate = Coordinate.valueOf(0, 0);
        universe.setLive(coordinate);
        boolean live = universe.isLive(coordinate);
        assertThat(live, is(true));
    }

    @Test
    public void setLive2() {
        Coordinate coordinate = Coordinate.valueOf(0, 0);
        universe.setLive(coordinate.x, coordinate.y);
        boolean live = universe.isLive(coordinate);
        assertThat(live, is(true));
    }

    @Test(expected = IllegalArgumentException.class)
    public void setLive3() {
        Coordinate coordinate = Coordinate.valueOf(-1, -1);
        universe.setLive(coordinate.x, coordinate.y);
        fail();
    }

    @Test
    public void getLiveNeighborCount() {
        Coordinate coordinate = Coordinate.valueOf(1, 1);
        universe.getLiveNeighborCount(coordinate);
        int numberOfNeighbors = universe.getLiveNeighborCount(coordinate);
        assertThat(numberOfNeighbors, is(5));

    }

    @Test
    public void getLiveNeighbors() {
        Coordinate coordinate = Coordinate.valueOf(1, 1);
        List<Coordinate> neighbors = universe.getLiveNeighbors(coordinate);

        List<Coordinate> expected = new ArrayList<>();
        expected.add(Coordinate.valueOf(0,1));
        expected.add(Coordinate.valueOf(0,2));
        expected.add(Coordinate.valueOf(1,0));
        expected.add(Coordinate.valueOf(1,2));
        expected.add(Coordinate.valueOf(2,0));
        assertThat(neighbors, containsInAnyOrder(expected.toArray()));
    }
}