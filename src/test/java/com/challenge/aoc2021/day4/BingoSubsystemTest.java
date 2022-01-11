package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;
import static org.testng.Assert.*;

public class BingoSubsystemTest {
    private BingoSubsystem bingoSubsystem;

    @BeforeMethod
    public void setUp() throws BingoSubsystemException {
        setPrintAssertionsDescription(true);
        bingoSubsystem = BingoSubsystem.Builder.createBingoSubsystem()
                .fromFile("BingoTest.txt")
                .forTest()
                .build();
    }

    @Test
    public void testCalculateScoreOfWinningBoard() throws BingoSubsystemException {
        final var score = bingoSubsystem.calculateScoreOfWinningBoard();

        Assertions.assertThat(score).as("Checking score of winning card").isEqualTo(4512);
    }
}