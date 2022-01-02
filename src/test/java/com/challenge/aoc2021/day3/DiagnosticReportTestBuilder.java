package com.challenge.aoc2021.day3;

import com.challenge.library.util.BitSetUtil;

public class DiagnosticReportTestBuilder {
    private DiagnosticReportTestBuilder() {}

    public static DiagnosticReport of(String... lines) {
        var diagnosticReport = new DiagnosticReport(lines[0].length());

        for (var line : lines) {
            diagnosticReport.addLine(BitSetUtil.fromStringToBitSet(line));
        }

        return diagnosticReport;
    }
}
