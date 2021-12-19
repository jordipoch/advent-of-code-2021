package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportAnalyzerException;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class Day3Test {

    @Test
    public void testRunPart1() throws DiagnosticReportAnalyzerException {
        assertEquals(Day3.runPart1(), 198);
    }
}