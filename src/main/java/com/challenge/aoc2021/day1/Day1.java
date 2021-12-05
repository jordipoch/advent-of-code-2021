package com.challenge.aoc2021.day1;

import com.challenge.aoc2021.day1.exception.DepthScannerCreationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2021.day1.DepthScanner.createDepthScanner;

public class Day1 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws DepthScannerCreationException {
        var result = runDay1();
        logger.info("Result day1: {}", result);
    }

    public static int runDay1() throws DepthScannerCreationException {
        var depthScanner = createDepthScanner("input.txt");
        return depthScanner.getNumIncreases();
    }
}
