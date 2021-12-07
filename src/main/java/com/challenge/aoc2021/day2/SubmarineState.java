package com.challenge.aoc2021.day2;

import java.util.Objects;

public class SubmarineState {
    private final int position;
    private final int depth;

    public static SubmarineState createInitialState() {
        return new SubmarineState();
    }

    public static SubmarineState of(int position, int depth) {
        return new SubmarineState(position, depth);
    }

    private SubmarineState() {
        this.position = 0;
        this.depth = 0;
    }

    private SubmarineState(int position, int depth) {
        this.position = position;
        this.depth = depth;
    }

    public SubmarineState move(Direction direction, int amount) {
        return direction.moveDirection(this, amount);
    }

    SubmarineState moveForward(int amount) {
        return of(position + amount, depth);
    }

    SubmarineState moveDown(int amount) {
        return of(position, depth + amount);
    }

    SubmarineState moveUp(int amount) {
        return of(position, depth - amount);
    }

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
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
        SubmarineState that = (SubmarineState) o;
        return position == that.position && depth == that.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, depth);
    }
}
