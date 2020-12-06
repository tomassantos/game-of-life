package com.tomas.gof;

public class Main {
    public static void main(String[] args) {
        int numberOfGenerations = 5;
        int size = 6;
        int seed = 4;

        Universe universe = new Universe(size, seed);
        System.out.println(universe);

        Rule rule = new BasicRule();

        for (int i = 0; i < numberOfGenerations; i++) {
            rule.apply(universe);
            System.out.println(universe);
        }
    }
}
