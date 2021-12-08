package com.challenge.aoc2021.day2;

public class SubmarineSimpleState extends SubmarineState {

    public static SubmarineState create() {
        return new SubmarineSimpleState();
    }

    public static SubmarineState of(int position, int depth) {
        return new SubmarineSimpleState(position, depth);
    }

    private SubmarineSimpleState() {
        super();
    }

    private SubmarineSimpleState(int position, int depth) {
        super(position, depth);
    }

    @Override
    SubmarineState moveForward(int amount) {
        return of(position + amount, depth);
    }

    @Override
    SubmarineState moveDown(int amount) {
        return of(position, depth + amount);
    }

    @Override
    SubmarineState moveUp(int amount) {
        return of(position, depth - amount);
    }

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                ", depth=" + depth +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
