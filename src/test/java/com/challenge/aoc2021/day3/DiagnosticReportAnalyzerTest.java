package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportAnalyzerException;
import org.testng.annotations.Test;

import static com.challenge.aoc2021.day3.DiagnosticReportAnalyzer.Builder.createDiagnosticReportAnalyzer;
import static org.testng.Assert.*;

public class DiagnosticReportAnalyzerTest {

    @Test
    public void testCalculatePowerConsumptionFromFile() throws DiagnosticReportAnalyzerException {
        var analyzer = createDiagnosticReportAnalyzer(DiagnosticReportReaderTest.BASE_PATH).build();
        var powerConsumption = analyzer.calculatePowerConsumption("DiagnosticReportTest.txt");

        assertEquals(powerConsumption, 198);
    }
}