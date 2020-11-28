package com.tomas.gof;

public class Main {
    public static void main(String[] args) {
        int numberOfGenerations = 1;
        int size = 3;
        int seed = 4;

        Universe universe = new Universe(size);
//        universe.setLive(2,1);
//        universe.setLive(2,2);
//        universe.setLive(2,3);
//        universe.setLive(1,2);
        System.out.println(universe);

        Rule rule = new BasicRule();

        for (int i = 0; i < numberOfGenerations; i++) {
            rule.apply(universe);
            System.out.println(universe);
            System.out.println(universe.toString().hashCode());
        }
    }
}
