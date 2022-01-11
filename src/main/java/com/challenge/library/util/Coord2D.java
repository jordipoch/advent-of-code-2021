package com.challenge.library.util;

import java.util.Objects;

public class Coord2D {
    private final int x;
    private final int y;

    private Coord2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coord2D of(int x, int y) {
        return new Coord2D(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord2D coord2D = (Coord2D) o;
        return x == coord2D.x && y == coord2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
