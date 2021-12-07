package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.SubmarineControllerException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Day2Test {

    @Test
    public void testRunPart1() throws SubmarineControllerException {
        assertEquals(Day2.runPart1(), 1693300);
    }
}