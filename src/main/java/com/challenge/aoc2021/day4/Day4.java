package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemCreationException;
import com.challenge.aoc2021.day4.exception.BingoPlayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2021.day4.PlayMode.LOSER;
import static com.challenge.aoc2021.day4.PlayMode.WINNER;

public class Day4 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws BingoPlayException, BingoSubsystemCreationException {
        final var resultPart1 = runPart1();
        System.out.println("Result of part 1: " + resultPart1);

        final var resultPart2 = runPart2();
        System.out.println("Result of part 2: " + resultPart2);
    }

    public static int runPart1() throws BingoSubsystemCreationException, BingoPlayException {
        try {
            var bingo = BingoSubsystemFactory.getInstance().create("input.txt", WINNER);
            final var score = bingo.play();
            logger.info("Score for the winning board: {}", score);
            return score;
        } catch (BingoPlayException | BingoSubsystemCreationException e) {
            logger.error("Bingo subsystem error while running part1!", e);
            throw e;
        }
    }

    public static int runPart2() throws BingoSubsystemCreationException, BingoPlayException {
        try {
            var bingo = BingoSubsystemFactory.getInstance().create("input.txt", LOSER);
            final var score = bingo.play();
            logger.info("Score for the losing board: {}", score);
            return score;
        } catch (BingoPlayException | BingoSubsystemCreationException e) {
            logger.error("Bingo subsystem error while running part2!", e);
            throw e;
        }
    }
}
