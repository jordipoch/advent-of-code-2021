package com.challenge.aoc2021.day2;

import java.util.Objects;

class Move {
    private final Direction direction;
    private final int amount;

    public static Move of(Direction dir, int amount) {
        return new Move(dir, amount);
    }

    private Move(Direction direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("{%s, %d}", direction, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return amount == move.amount && direction == move.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, amount);
    }
}
