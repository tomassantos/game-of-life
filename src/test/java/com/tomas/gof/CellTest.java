package com.tomas.gof;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CellTest {

    @Test
    public void isLive() {
        Cell cell = new Cell();
        cell.setDead();
        cell.setLive();

        boolean live = cell.isLive();
        assertThat(live, is(true));
    }

    @Test
    public void isDead() {
        Cell cell = new Cell();
        cell.setLive();
        cell.setDead();

        boolean isDead = cell.isDead();
        assertThat(isDead, is(true));
    }

    @Test
    public void setDead() {
        Cell cell = new Cell();
        cell.setLive();
        cell.setDead();

        boolean isDead = cell.isDead();
        assertThat(isDead, is(true));
    }

    @Test
    public void setLive() {
        Cell cell = new Cell();
        cell.setDead();
        cell.setLive();

        boolean live = cell.isLive();
        assertThat(live, is(true));
    }

    @Test
    public void wasDead() {
        Cell cell = new Cell();
        cell.setDead();
        cell.setLive();

        boolean wasDead = cell.wasDead();
        assertThat(wasDead, is(true));
    }

    @Test
    public void wasDead2() {
        Cell cell = new Cell();
        cell.setLive();
        cell.setLive();

        boolean wasDead = cell.wasDead();
        assertThat(wasDead, is(false));
    }

    @Test
    public void wasAlive() {
        Cell cell = new Cell();
        cell.setLive();
        cell.setDead();

        boolean wasAlive = cell.wasAlive();
        assertThat(wasAlive, is(true));
    }

    @Test
    public void wasAlive2() {
        Cell cell = new Cell();
        cell.setDead();
        cell.setDead();

        boolean wasAlive = cell.wasAlive();
        assertThat(wasAlive, is(false));
    }
}