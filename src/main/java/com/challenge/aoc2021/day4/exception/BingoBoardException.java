package com.challenge.aoc2021.day4.exception;

public class BingoBoardException extends Exception {
    public BingoBoardException(int num) {
        super(String.format("Error trying to build the bingo board. Wrong size: %d", num));
    }
}
