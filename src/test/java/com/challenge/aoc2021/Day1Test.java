package com.challenge.aoc2021;

import com.challenge.aoc2021.day1.Day1;
import com.challenge.aoc2021.day1.exception.DepthScannerCreationException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Day1Test {

    @Test
    public void testRunPart1() throws DepthScannerCreationException {
        assertEquals(Day1.runPart1(), 1832);
    }

    @Test
    public void testRunPart2() throws DepthScannerCreationException {
        assertEquals(Day1.runPart2(), 1858);
    }
}