package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportReaderException;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import static java.lang.Long.parseLong;
import static org.testng.Assert.*;

public class DiagnosticReportReaderTest {
    public static final Path BASE_PATH = Paths.get("src", "test", "resources", "com", "challenge", "aoc2021", "day3");

    @Test
    public void testRead() throws DiagnosticReportReaderException {
        var reader = DiagnosticReportReader.createDiagnosticReportReader(BASE_PATH);
        var diagnosticReport = reader.read("ReaderTest.txt");

        List<BitSet> expected = buildExpected(
                "1011",
                "0110",
                "1101");

        Assertions.assertThat(diagnosticReport).as("Checking diagnostic report read...").isEqualTo(expected);
    }

    private List<BitSet> buildExpected(String... lines) {
        var bitSetList = new ArrayList<BitSet>();
        for (var line : lines)
            bitSetList.add(BitSet.valueOf(new long[] {parseLong(StringUtils.reverse(line), 2)}));
        return bitSetList;
    }
}