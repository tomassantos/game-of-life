package com.tomas.gof;

import static com.tomas.gof.Cell.State.*;

public class Cell {
    public enum State { ALIVE, DEAD }

    private State previous = DEAD;
    private State current = DEAD;

    public Cell() { }

    private Cell(State state) {
        this.current = state;
        this.previous = state;
    }

    /**
     *
     * @return true if current cell state is alive
     */
    public boolean isLive() {
        return current == ALIVE;
    }

    /**
     * @return true if current cell state is dead
     */
    public boolean isDead() {
        return current == DEAD;
    }

    /**
     * current state of cell becomes dead
     */
    public void setDead() {
        setState(DEAD);
    }

    /**
     * current state of cell becomes alive
     */
    public void setLive() {
        setState(ALIVE);
    }

    /**
     * @return current state
     */
    public State getState() {
        return current;
    }

    /**
     * @return true if cell previous state was dead and
     * current state is alive
     */
    public boolean wasDead() {
        return this.current == ALIVE && this.previous == DEAD;
    }

    /**
     * @return true if cell previous state was alive and
     * current state is dead
     */
    public boolean wasAlive() {
        return this.current == DEAD && this.previous == ALIVE;
    }

    /**
     * @param state of cell to change to
     */
    public void setState(State state) {
        this.previous = this.current;
        this.current = state;
    }

    public static Cell valueOf(State state) {
        return new Cell(state);
    }
}
