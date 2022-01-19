package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemCreationException;
import com.challenge.aoc2021.day4.exception.BingoPlayException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.challenge.aoc2021.day4.PlayMode.LOSER;
import static com.challenge.aoc2021.day4.PlayMode.WINNER;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

public class BingoSubsystemTest {

    @BeforeMethod
    public void setUp() {
        setPrintAssertionsDescription(true);
    }

    @Test
    public void testCalculateScoreOfWinningBoard() throws BingoPlayException, BingoSubsystemCreationException {
        var bingoSubsystem = createBingoSubsystem(WINNER);
        final var score = bingoSubsystem.play();

        Assertions.assertThat(score).as("Checking score of winning card").isEqualTo(4512);
    }

    @Test
    public void testCalculateScoreOfLosingBoard() throws BingoPlayException, BingoSubsystemCreationException {
        var bingoSubsystem = createBingoSubsystem(LOSER);
        final var score = bingoSubsystem.play();

        Assertions.assertThat(score).as("Checking score of losing card").isEqualTo(1924);
    }

    private BingoSubsystem createBingoSubsystem(PlayMode playMode) throws BingoSubsystemCreationException {
        return BingoSubsystemFactory.getInstance()
                .createForTest("BingoTest.txt", playMode);
    }
}