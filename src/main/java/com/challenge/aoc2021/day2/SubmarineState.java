package com.challenge.aoc2021.day2;

import java.util.Objects;

public abstract class SubmarineState {
    protected final int position;
    protected final int depth;

    protected SubmarineState() {
        position = 0;
        depth = 0;
    }

    protected SubmarineState(int position, int depth) {
        this.position = position;
        this.depth = depth;
    }

    public SubmarineState move(Direction direction, int amount) {
        return direction.moveDirection(this, amount);
    }

    abstract SubmarineState moveForward(int amount);

    abstract SubmarineState moveDown(int amount);

    abstract SubmarineState moveUp(int amount);

    public int getPosition() {
        return position;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubmarineState)) return false;
        SubmarineState that = (SubmarineState) o;
        return position == that.position && depth == that.depth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, depth);
    }
}
