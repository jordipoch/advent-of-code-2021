package com.challenge.aoc2021.day3;

public class LifeSupportCalculatorFactory {
    private static final LifeSupportCalculatorFactory INSTANCE = new LifeSupportCalculatorFactory();

    public static LifeSupportCalculatorFactory getInstance() {
        return INSTANCE;
    }

    public LifeSupportCalculator createOxygenGeneratorRating(DiagnosticReport diagnosticReport) {
        return new OxygenGeneratorRatingCalculator(diagnosticReport);
    }

    public LifeSupportCalculator createCO2ScrubberRating(DiagnosticReport diagnosticReport) {
        return new CO2ScrubberRatingCalculator(diagnosticReport);
    }
}
