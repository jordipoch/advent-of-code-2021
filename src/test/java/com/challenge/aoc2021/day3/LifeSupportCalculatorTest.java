package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportReaderException;

import static com.challenge.aoc2021.day3.DiagnosticReportReader.createDiagnosticReportReader;

public class LifeSupportCalculatorTest {

    protected LifeSupportCalculator instance;

    protected DiagnosticReport createDiagnosticReport() throws DiagnosticReportReaderException {
        DiagnosticReportReader reader = createDiagnosticReportReader(DiagnosticReportReaderTest.BASE_PATH);
        DiagnosticReport report = reader.read("DiagnosticReportTest.txt");
        System.out.println(report);
        return report;
    }
}
