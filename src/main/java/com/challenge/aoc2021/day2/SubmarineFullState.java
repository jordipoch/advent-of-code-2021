package com.challenge.aoc2021.day2;

public class SubmarineFullState extends SubmarineState {
    private final int aim;

    public static SubmarineState create() {
        return new SubmarineFullState();
    }

    public static SubmarineState of(int position, int depth, int aim) {
        return new SubmarineFullState(position, depth, aim);
    }

    private SubmarineFullState() {
        super();
        aim = 0;
    }

    private SubmarineFullState(int position, int depth, int aim) {
        super(position, depth);
        this.aim = aim;
    }

    @Override
    SubmarineState moveForward(int amount) {
        return of(position + amount, depth + aim*amount, aim);
    }

    @Override
    SubmarineState moveDown(int amount) {
        return of(position, depth, aim + amount);
    }

    @Override
    SubmarineState moveUp(int amount) {
        return of(position, depth, aim - amount);
    }

    @Override
    public String toString() {
        return "{" +
                "position=" + position +
                ", depth=" + depth +
                ", aim=" + aim +
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
