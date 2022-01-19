package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoBoardException;
import com.challenge.aoc2021.day4.exception.BingoException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.challenge.aoc2021.day4.Bingo.*;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

public class BingoTest {
    private static final int[] NUMBERS_TO_DRAW_ARRAY = BingoTestData.NUMBERS_TO_DRAW;
    private static final int[][] BOARDS_NUMBERS_ARRAY = BingoTestData.BOARDS;

    private Bingo bingo;

    @BeforeMethod
    public void setUp() throws BingoBoardException {
        setPrintAssertionsDescription(true);
        createDefaultBingo();
    }

    private void createDefaultBingo() throws BingoBoardException {
        bingo = createBingo(BOARDS_NUMBERS_ARRAY, NUMBERS_TO_DRAW_ARRAY);
    }

    private Bingo createBingo(int[][] boardNumbersArray, int[] numbersToDrawArray) throws BingoBoardException {
        List<BingoBoard> bingoBoards = BingoTestUtils.createBingoBoardsFromArray(boardNumbersArray);
        List<Integer> numbersToDraw = Arrays.stream(numbersToDrawArray).boxed().collect(Collectors.toList());
        return Builder.createBingo()
                .withBoardsToPlay(bingoBoards)
                .withNumbersToDraw(numbersToDraw)
                .build();
    }

    @Test (expectedExceptions = BingoException.class)
    public void testNoNumbersToDrawThrowException() throws BingoBoardException, BingoException {
        var bingoNoNumbers = createBingo(BOARDS_NUMBERS_ARRAY, new int[] {});
        bingoNoNumbers.drawNextNumber();
    }



    @Test
    public void testDraw1NumberNoWinningBoard() throws BingoException {
        var result = bingo.drawNextNumber();

        Assertions.assertThat(result.getWinningBoards())
                .as("Checking winning boards after drawing number...")
                .isEmpty();

        Assertions.assertThat(result.getNumberDrawn())
                .as("Checking the last number drawn...")
                .isEqualTo(NUMBERS_TO_DRAW_ARRAY[0]);

        Assertions.assertThat(result.getNumRemainingBoards())
                .as("Checking num of remaining boards")
                .isEqualTo(BOARDS_NUMBERS_ARRAY.length);

        Assertions.assertThat(result.getNumbersLeftToDraw())
                .as("Checking numbers left to draw")
                .isEqualTo(NUMBERS_TO_DRAW_ARRAY.length - 1);

    }

    @Test
    public void testDrawUntilFirstWinningBoard() throws BingoException {
        var result = drawUntilFirstWinnerFound();

        Assertions.assertThat(result.getWinningBoards())
                .as("Checking winning boards after drawing number...")
                .isNotEmpty();

        Assertions.assertThat(result.getNumRemainingBoards())
                .as("Checking num of remaining boards")
                .isEqualTo(BOARDS_NUMBERS_ARRAY.length - 1);
    }

    @Test
    public void testDrawUntilLastWinningBoard() throws BingoException {
        var result = drawUntilLastWinnerFound();

        Assertions.assertThat(result.getWinningBoards())
                .as("Checking winning boards after drawing number...")
                .isNotEmpty();

        Assertions.assertThat(result.getNumRemainingBoards())
                .as("Checking num of remaining boards")
                .isZero();
    }

    private DrawingResult drawUntilFirstWinnerFound() throws BingoException {
        DrawingResult result;
        do {
            result = bingo.drawNextNumber();
        } while (!result.areWinnersFound());

        return result;
    }

    private DrawingResult drawUntilLastWinnerFound() throws BingoException {
        DrawingResult result;
        do {
            result = bingo.drawNextNumber();
        } while (!result.areLastWinnersFound());

        return result;
    }
}