package com.challenge.aoc2021;

import com.challenge.aoc2021.day1.Day1;
import com.challenge.aoc2021.day1.exception.DepthScannerCreationException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Day1Test {

    @Test
    public void testRunDay1() throws DepthScannerCreationException {
        assertEquals(Day1.runDay1(), 1832);
    }
}