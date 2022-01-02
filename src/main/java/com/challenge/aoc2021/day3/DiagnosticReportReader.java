package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportReaderException;
import com.challenge.library.files.TextFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;

public class DiagnosticReportReader {
    private static final Path DEFAULT_BASE_PATH = Paths.get("src","main", "resources", "com", "challenge", "aoc2021", "day3");
    private final Path basePath;

    public static DiagnosticReportReader createDiagnosticReportReader() {
        return new DiagnosticReportReader(DEFAULT_BASE_PATH);
    }

    public static DiagnosticReportReader createDiagnosticReportReader(Path alternateBasePath) {
        return new DiagnosticReportReader(alternateBasePath);
    }

    private DiagnosticReportReader(Path basePath) {
        this.basePath = basePath;
    }

    public DiagnosticReport read(String fileName) throws DiagnosticReportReaderException {
        var lines = readTextLines(fileName);
        return buildDiagnosticReport(lines);
    }

    private List<String> readTextLines(String fileName) throws DiagnosticReportReaderException {
        Path filePath = basePath.resolve(fileName);
        try {
            return TextFileReader.readAllLinesFromFile(filePath);
        } catch (IOException e) {
            throw new DiagnosticReportReaderException("Cannot read the diagnostic report from " + fileName, e);
        }
    }

    private DiagnosticReport buildDiagnosticReport(List<String> lines) {
        var diagnosticReport = new DiagnosticReport(lines.get(0).length());
        for (var line: lines) {
            diagnosticReport.addLine(createBitSet(line));
        }

        return diagnosticReport;
    }

    private BitSet createBitSet(String line) {
        var bitSet = new BitSet(line.length());
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '1') {
                bitSet.set(i);
            }
        }

        return bitSet;
    }
}
