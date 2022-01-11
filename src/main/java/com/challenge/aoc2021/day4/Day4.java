package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2021.day4.BingoSubsystem.Builder.createBingoSubsystem;

public class Day4 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws BingoSubsystemException {
        final var resultPart1 = runPart1();
        System.out.println("Result of part 1: " + resultPart1);
    }

    public static int runPart1() throws BingoSubsystemException {
        try {
            var bingo = createBingoSubsystem()
                    .fromFile("input.txt")
                    .build();
            final var score = bingo.calculateScoreOfWinningBoard();
            logger.info("Score for the winning board: {}", score);
            return score;
        } catch (BingoSubsystemException e) {
            logger.error("Bingo subsystem error while running part1!", e);
            throw e;
        }
    }
}
