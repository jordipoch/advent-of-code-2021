package com.challenge.aoc2021.day4.exception;

public class BingoSubsystemException extends Exception {
     public BingoSubsystemException(String message, Throwable t) {
         super(message, t);
     }

    public BingoSubsystemException(String message) {
        super(message);
    }
}
