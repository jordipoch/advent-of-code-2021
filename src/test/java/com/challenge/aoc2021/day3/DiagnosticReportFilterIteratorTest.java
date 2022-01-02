package com.challenge.aoc2021.day3;

import com.challenge.library.util.BitSetUtil;
import org.assertj.core.api.Assertions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.*;

public class DiagnosticReportFilterIteratorTest {
    private final String[] diagnosticReportLines = {
            "10011001",
            "01101101",
            "11000111",
            "00010010"
    };
    private DiagnosticReport diagnosticReport;
    private DiagnosticReportFilter diagnosticReportFilter;
    private DiagnosticReportFilterIterator iterator;

    @BeforeClass
    public void setUp() {
        diagnosticReport = DiagnosticReportTestBuilder.of(diagnosticReportLines);
    }

    @BeforeMethod
    public void setUpTest() {
        diagnosticReportFilter = new DiagnosticReportFilter(diagnosticReport);
        iterator = diagnosticReportFilter.iterator();
    }

    @Test
    public void testSimpleIteration() {
        var lineNum = 0;
        while(iterator.hasNext()) {
            var line = iterator.next();
            assertThat(line)
                    .as("Checking the expected line is retrieved in the current iteration")
                    .isEqualTo(BitSetUtil.fromStringToBitSet(diagnosticReportLines[lineNum++]));
        }

        assertThat(lineNum).as("Checking the whole iteration has completed").isEqualTo(diagnosticReportLines.length);
    }

    @Test (expectedExceptions = NoSuchElementException.class)
    public void testNextThrowNoSuchElementExceptionsIfTheresNoNext() {
        while(iterator.hasNext()) {
            iterator.next();
        }

        iterator.next();
    }

    @Test
    public void testIterationWithFiltering() {
        filterLinesThatStartWith1();

        assertThat(diagnosticReportFilter.getNumActiveLines())
                .as("Checking number active lines after filtering...")
                .isEqualTo(2);

        checkAllRemainingLinesStartWith0();
    }

    @Test (expectedExceptions = IllegalStateException.class)
    public void testRemoveThrowsIllegalStateExceptionIfNextIsNotCalledFirst() {
        iterator.remove();
    }

    private void checkAllRemainingLinesStartWith0() {
        iterator = diagnosticReportFilter.iterator();
        while(iterator.hasNext()) {
            var line = iterator.next();
            assertThat(line.get(0))
                    .as("Checking that the first bit is 0...")
                    .isFalse();
        }
    }

    private void filterLinesThatStartWith1() {
        while(iterator.hasNext()) {
            var line = iterator.next();
            if (line.get(0)) {
                iterator.remove();
            }
        }
    }
}