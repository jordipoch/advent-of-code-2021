package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoBoardException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.challenge.aoc2021.day4.BingoBoard.Builder.createBingoBoard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

public class BingoBoardTest {
    private static final Logger logger = LogManager.getLogger();

    private final int[] bingoNumbers = {
            14,21,17,24, 4,
            10,16,15, 9,19,
            18, 8,23,26,20,
            22,11,13, 6, 5,
             2, 0,12, 3, 7
    };

    private BingoBoard board;

    @BeforeMethod
    public void setUp() throws BingoBoardException {
        setPrintAssertionsDescription(true);
        board = createBingoBoard(bingoNumbers).build();
    }

    @Test
    public void testBingoBoardCreation() {
        assertThat(board.getSize())
                .as("Checking board size...")
                .isEqualTo(5);

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j <board.getSize(); j++) {
                checkBoardNumberAtPosition(j, i);
            }
        }
    }

    @Test (expectedExceptions = BingoBoardException.class)
    public void testBingoBoardCreationWrongSize() throws BingoBoardException {
        var bingoNumbersWrongSize = new int[] {
                14,21,17,24, 4,
                10,16,15, 9,19,
                18, 8,23,26,20,
                22,11,13, 6, 5,
                2, 0,12, 3, 7,
                99
        };

        createBingoBoard(bingoNumbersWrongSize).build();
    }

    @Test
    public void testMarkNumbers() {
        final var existingNumMarked = 23;
        final var existingNumNotMarked = 24;
        final var nonExistingNumMarked = 34;
        final var nonExistingNumNotMarked = 25;


        board.markIfExists(existingNumMarked);
        board.markIfExists(nonExistingNumMarked);

        checkMarked(existingNumMarked);
        checkNotMarked(existingNumNotMarked, nonExistingNumMarked, nonExistingNumNotMarked);
    }

    @Test
    public void testFillVerticalLine() {
        markNumbersAndCheckResult(false, 26, 24, 3, 6);
        markNumbersAndCheckResult(true, 9);

        logger.debug("Content of the board after completing line:\n{}", board);
    }

    @Test
    public void testFillHorizontalLine() {
        markNumbersAndCheckResult(false, 8, 26, 18, 20);
        markNumbersAndCheckResult(true, 23);

        logger.debug("Content of the board after completing line:\n{}", board);
    }

    @Test
    public void testGetSumOfUnmarkedNumbers() {
        markNumbersAndCheckResult(false, 7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21);
        markNumbersAndCheckResult(true, 24);

        var sum = board.getSumOfUnmarkedNumbers();
        assertThat(sum).as("Checking the sum of unmarked numbers after filling a line...").isEqualTo(188);
    }

    private void markNumbersAndCheckResult(boolean expectedResult, int... nums) {
        for(var num : nums) {
            var result = board.markIfExists(num);
            assertThat(result).as("Checking result of marking number %d is %b", num, expectedResult).isEqualTo(expectedResult);
        }
    }

    @Test
    public void testToString() {
        board.markIfExists(14);
        board.markIfExists(23);
        board.markIfExists(9);

        var expectedString =
                " X14  21  17  24   4\n" +
                "  10  16  15  X9  19\n" +
                "  18   8 X23  26  20\n" +
                "  22  11  13   6   5\n" +
                "   2   0  12   3   7\n";


        final var toString = board.toString();

        assertThat(toString).as("Checking the result of toString...").isEqualTo(expectedString);
    }

    private void checkMarked(int num) {
        assertThat(board.isNumberMarked(num))
                .as("Checking if number %d is marked...", num)
                .isTrue();
    }

    private void checkNotMarked(int... nums) {
        for (var num : nums) {
            assertThat(board.isNumberMarked(num))
                    .as("Checking if number %d is not marked...", num)
                    .isFalse();
        }
    }

    private void checkBoardNumberAtPosition(int x, int y) {
        var expectedNumber = bingoNumbers[board.getSize() * y + x];

        assertThat(board.getNumberAtPosition(x, y))
                .as("Checking position (%d,%d) in the board is %d...", x, y, expectedNumber)
                .isEqualTo(expectedNumber);
    }
}