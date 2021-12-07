package com.challenge.aoc2021.day2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Direction {
    FORWARD ("forward"){
        SubmarineState moveDirection(SubmarineState submarineState, int amount) {
            return submarineState.moveForward(amount);
        }
    },
    DOWN ("down"){
        SubmarineState moveDirection(SubmarineState submarineState, int amount) {
            return submarineState.moveDown(amount);
        }
    },
    UP ("up"){
        SubmarineState moveDirection(SubmarineState submarineState, int amount) {
            return submarineState.moveUp(amount);
        }
    };

    Direction(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private final String name;
    private static Map<String, Direction> stringDirectionMap = Arrays.stream(values()).collect(Collectors.toMap(Direction::toString, Function.identity()));

    public static Direction fromString(String name) {
        return stringDirectionMap.get(name);
    }

    abstract SubmarineState moveDirection(SubmarineState submarineState, int amount);
}
