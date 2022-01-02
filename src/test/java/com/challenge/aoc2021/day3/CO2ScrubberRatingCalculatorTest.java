package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportReaderException;
import com.challenge.aoc2021.day3.exception.LifeSupportCalculatorException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CO2ScrubberRatingCalculatorTest extends LifeSupportCalculatorTest {
    @BeforeMethod
    public void setUp() throws DiagnosticReportReaderException {
        DiagnosticReport report = createDiagnosticReport();
        instance = LifeSupportCalculatorFactory.getInstance().createCO2ScrubberRating(report);
    }

    @Test
    public void testCalculate() throws LifeSupportCalculatorException {
        var oxygenGeneratorRating = instance.calculate();

        Assertions.assertThat(oxygenGeneratorRating).as("Checking calculated CO2 Scrubber Rating...").isEqualTo(10);
    }
}