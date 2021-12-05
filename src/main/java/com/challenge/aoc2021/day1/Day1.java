package com.challenge.aoc2021.day1;

import com.challenge.aoc2021.day1.exception.DepthScannerCreationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2021.day1.DepthScanner.createDepthScanner;

public class Day1 {
    private static final Logger logger = LogManager.getLogger();
    private static final String INPUT_FILE = "input.txt";

    public static void main(String[] args) throws DepthScannerCreationException {
        runPart1();
        runPart2();
    }

    public static int runPart1() throws DepthScannerCreationException {

        var depthScanner = createDepthScanner(INPUT_FILE);
        final var result = depthScanner.getNumIncreases();
        logger.info("Result day1: {}", result);
        
        return result;
    }

    public static int runPart2() throws DepthScannerCreationException {
        var depthScanner = createDepthScanner(INPUT_FILE);
        final var result = depthScanner.getNumWindowIncreases();
        logger.info("Result day2: {}", result);

        return result;
    }
}
