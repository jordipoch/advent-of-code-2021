package com.challenge.aoc2021.day3;

import com.challenge.library.util.BitSetUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DiagnosticReportFilterTest {
    private DiagnosticReportFilter diagnosticReportFilter;

    @BeforeMethod
    public void setUp() {
        var diagnosticReport = DiagnosticReportTestBuilder.of(
                "10011001",
                "01101101",
                "11000111",
                "00010010");

        diagnosticReportFilter = new DiagnosticReportFilter(diagnosticReport);
    }

    @Test
    public void testGetFirstActiveLineIndex() {
        var index = diagnosticReportFilter.getFirstActiveLineIndex();
        assertThat(index).as("Checking first active line in report...").isZero();
    }

    @Test
    public void testGetNextActiveLineIndex() {
        var index = diagnosticReportFilter.getNextActiveLineIndex(0);
        assertThat(index).as("Checking next active line index in report...").isEqualTo(1);
    }

    @Test
    public void testGetNextActiveLineIndex_noSuchLine() {
        var index = diagnosticReportFilter.getNextActiveLineIndex(3);
        assertThat(index).as("Checking next active line index in report...").isEqualTo(-1);
    }

    @Test
    public void testGetLineAtPosition() {
        final var expected = BitSetUtil.fromStringToBitSet("11000111");
        final var position = 2;
        var line = diagnosticReportFilter.getLine(position);

        assertThat(line).as("Checking line at position {}...", position).isEqualTo(expected);
    }

    @Test
    public void testFilterLine() {
        var numLines = diagnosticReportFilter.filterLine(1);
        assertThat(numLines).as("Checking num lines left after filtering...").isEqualTo(3);
    }

    @Test
    public void testFilterMoveAndGetLine() {
        diagnosticReportFilter.filterLine(0);
        var numLinesLeft = diagnosticReportFilter.filterLine(2);
        assertThat(numLinesLeft).as("Checking num lines left in diagnostic report after filtering...").isEqualTo(2);

        var lineNum = diagnosticReportFilter.getFirstActiveLineIndex();
        assertThat(lineNum).as("Checking first active line index...").isEqualTo(1);

        lineNum = diagnosticReportFilter.getNextActiveLineIndex(lineNum);
        assertThat(lineNum).as("Checking next active line index...").isEqualTo(3);

        var line = diagnosticReportFilter.getLine(lineNum);
        var expectedLine = BitSetUtil.fromStringToBitSet("00010010");
        assertThat(line).as("Check that the right line is retrieved...").isEqualTo(expectedLine);

        lineNum = diagnosticReportFilter.getNextActiveLineIndex(lineNum);
        assertThat(lineNum).as("Checking that there are no more active lines...").isEqualTo(-1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTryGettingFilteredOutLine() {
        diagnosticReportFilter.filterLine(2);
        diagnosticReportFilter.getLine(2);
    }
}