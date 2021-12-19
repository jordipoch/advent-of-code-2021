package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportAnalyzerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.challenge.aoc2021.day3.DiagnosticReportAnalyzer.Builder.createDiagnosticReportAnalyzer;

public class Day3 {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws DiagnosticReportAnalyzerException {
        runPart1();
    }

    public static int runPart1() throws DiagnosticReportAnalyzerException {
        var diagnosticReportAnalyzer = createDiagnosticReportAnalyzer().build();
        var result = diagnosticReportAnalyzer.calculatePowerConsumption("input.txt");

        logger.info("Result of Day 3, part 1: {}", result);

        return 0;
    }
}
