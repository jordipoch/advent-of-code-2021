package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoBoardException;
import com.challenge.aoc2021.day4.exception.BingoSubsystemReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.challenge.aoc2021.day4.BingoBoard.Builder.createBingoBoard;
import static com.challenge.aoc2021.day4.BingoSubsystemReader.createForTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

public class BingoSubsystemReaderTest {
    private static final Logger logger = LogManager.getLogger();
    private static int[] EXPECTED_NUMBERS_ARRAY = {7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1};
    private static int[][] EXPECTED_BOARDS = {
            {22, 13, 17, 11, 0, 8, 2, 23, 4, 24, 21, 9, 14, 16, 7, 6, 10, 3, 18, 5, 1, 12, 20, 15, 19},
            {3, 15, 0, 2, 22, 9, 18, 13, 17, 5, 19, 8, 7, 25, 23, 20, 11, 10, 24, 4, 14, 21, 16, 12, 6},
            {14, 21, 17, 24, 4, 10, 16, 15, 9, 19, 18, 8, 23, 26, 20, 22, 11, 13, 6, 5, 2, 0, 12, 3, 7}
    };
    private BingoSubsystemReader reader;

    @BeforeMethod
    public void setUp() {
        setPrintAssertionsDescription(true);
        reader = createForTest();
    }

    @Test (expectedExceptions = BingoSubsystemReaderException.class)
    public void testReadNonExistingFile() throws BingoSubsystemReaderException {
        reader.read("non_existing_file.txt");
    }

    @Test
    public void testReadBingoSubsystem() throws BingoSubsystemReaderException, BingoBoardException {
        var components = reader.read("BingoTest.txt");

        final var expectedNumbers = Arrays.stream(EXPECTED_NUMBERS_ARRAY).boxed().collect(Collectors.toList());
        logger.debug("Numbers to draw: {}", components.getNumbersToDraw());

        assertThat(components.getNumbersToDraw()).as("Checking list of numbers to draw...")
                .isEqualTo(expectedNumbers);

        final var expectedBoards = buildExpectedBoards();
        logger.debug("Bingo boards read:\n{}", components.getBoards());

        assertThat(components.getBoards()).as("Checking bingo boards...")
                .isEqualTo(expectedBoards);
    }

    private List<BingoBoard> buildExpectedBoards() throws BingoBoardException {
        List<BingoBoard> boards = new ArrayList<>();
        for (var boardNumbers : EXPECTED_BOARDS) {
                boards.add(createBingoBoard(boardNumbers).build());
        }
        return boards;
    }
}