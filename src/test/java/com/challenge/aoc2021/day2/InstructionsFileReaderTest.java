package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.InstructionsFileReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.challenge.aoc2021.day2.Direction.DOWN;
import static com.challenge.aoc2021.day2.Direction.FORWARD;
import static com.challenge.aoc2021.day2.Direction.UP;
import static com.challenge.aoc2021.day2.Move.of;
import static org.testng.Assert.*;

public class InstructionsFileReaderTest {
    private static final Logger logger = LogManager.getLogger();
    public static final Path BASE_PATH = Paths.get("src", "test", "resources", "com", "challenge", "aoc2021", "day2");

    @Test
    public void testReadInstructions() throws InstructionsFileReaderException {
        var ifr = new InstructionsFileReader(BASE_PATH);
        //var filePath = createFilePath("InstructionsTestData1.txt");

        var instructions = ifr.readInstructions("InstructionsTestData1.txt");

        logger.debug("Instructions read: {}", instructions);

        Assertions.assertThat(instructions).as("Checking read instructions...").containsExactly(
                of(FORWARD, 5),
                of(DOWN, 5),
                of(FORWARD, 8),
                of(UP, 3),
                of(DOWN, 8),
                of(FORWARD, 2)
        );

    }

    private Path createFilePath(String filename) {
        return BASE_PATH.resolve(filename);
    }
}