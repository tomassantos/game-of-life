package com.tomas.gof;

import static com.tomas.gof.Cell.State.*;

public class Cell {
    public enum State { ALIVE, DEAD }

    private State state = DEAD;

    public boolean isLive() {
        return state == ALIVE;
    }

    public boolean isDead() {
        return state == DEAD;
    }

    public void setDead() {
        setState(DEAD);
    }

    public void setLive() {
        setState(ALIVE);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
