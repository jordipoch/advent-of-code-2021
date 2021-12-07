package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.SubmarineControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day2 {
    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws SubmarineControllerException {
        runPart1();
    }

    public static int runPart1() throws SubmarineControllerException {
        var submarineController = SubmarineController.Builder.createSubmarineController("input.txt").build();
        var result = submarineController.pilotSubmarine();

        logger.info("Result of Day 2, part 1: {}", result);
        return result;
    }
}
