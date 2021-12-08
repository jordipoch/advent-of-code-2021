package com.challenge.aoc2021.day2;

public class SubmarineStateFactory {
    private static final SubmarineStateFactory INSTANCE = new SubmarineStateFactory();

    public static SubmarineStateFactory getInstance() {
        return INSTANCE;
    }

    public SubmarineState createSubmarineSimpleState() {
        return SubmarineSimpleState.create();
    }

    public SubmarineState createSubmarineFullState() {
        return SubmarineFullState.create();
    }
}
