package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.SubmarineControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2021.day2.SubmarineController.Builder.createSubmarineController;

public class Day2 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SubmarineControllerException {
        runPart1();
        runPart2();
    }

    public static int runPart1() throws SubmarineControllerException {
        var submarineController = createSubmarineController("input.txt").build();
        var result = submarineController.pilotSubmarine();

        logger.info("Result of Day 2, part 1: {}", result);
        return result;
    }

    public static int runPart2() throws SubmarineControllerException {
        var submarineController = createSubmarineController("input.txt")
                .withFullState()
                .build();
        var result = submarineController.pilotSubmarine();

        logger.info("Result of Day 2, part 2: {}", result);
        return result;
    }
}
