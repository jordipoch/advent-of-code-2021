package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoBoardException;
import com.challenge.aoc2021.day4.exception.BingoSubsystemReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.challenge.aoc2021.day4.Bingo.Builder.createBingo;
import static com.challenge.aoc2021.day4.BingoReader.createForTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

public class BingoReaderTest {
    private static final Logger logger = LogManager.getLogger();
    private static final int[] EXPECTED_NUMBERS_ARRAY = BingoTestData.NUMBERS_TO_DRAW;
    private static final int[][] EXPECTED_BOARDS = BingoTestData.BOARDS;
    private BingoReader reader;

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
        var bingo = reader.read("BingoTest.txt");
        var expectedBingo = createBingo()
                .withNumbersToDraw(Arrays.stream(EXPECTED_NUMBERS_ARRAY).boxed().collect(Collectors.toList()))
                .withBoardsToPlay(BingoTestUtils.createBingoBoardsFromArray(EXPECTED_BOARDS))
                .build();

        logger.debug("Bingo read:\n{}", bingo);

        assertThat(bingo).as("Checking the bingo read has the same content as the expected one...")
                .isEqualTo(expectedBingo);
    }
}