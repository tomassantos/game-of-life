package com.tomas.gof;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BasicRuleTest {

    private BasicRule rule;

    @Before
    public void setUp() {
        rule = new BasicRule();
    }

    @Test
    public void apply() {
        Universe universe = new Universe(6);
        universe.setLive(2,1);
        universe.setLive(2,2);
        universe.setLive(2,3);
        universe.setLive(1,2);
        rule.apply(universe);

        assertThat(universe.toString().hashCode(), is(1451154481));
    }

    @Test
    public void apply2() {
        Universe universe = new Universe(6);
        universe.setLive(1,2);
        universe.setLive(2,2);
        universe.setLive(2,3);
        universe.setLive(3,3);
        rule.apply(universe);
        rule.apply(universe);

        assertThat(universe.toString().hashCode(), is(1555425056));
    }

    @Test
    public void apply3() {
        Universe universe = new Universe(4);
        universe.setLive(1,1);
        universe.setLive(1,2);
        universe.setLive(2,1);
        universe.setLive(2,2);
        rule.apply(universe);

        assertThat(universe.toString().hashCode(), is(-613621632));
    }
}