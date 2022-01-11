package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.DiagnosticReportAnalyzerException;
import com.challenge.aoc2021.day3.exception.DiagnosticReportReaderException;
import com.challenge.aoc2021.day3.exception.LifeSupportCalculatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.BitSet;

import static com.challenge.aoc2021.day3.DiagnosticReportReader.createDiagnosticReportReader;
import static com.challenge.library.util.BitSetUtil.fromBitSetToInt;
import static com.challenge.library.util.BitSetUtil.fromBitSetToString;

public class DiagnosticReportAnalyzer {
    private static final Logger logger = LogManager.getLogger();

    private int[] count1s;
    private final DiagnosticReportReader diagnosticReportReader;

    private DiagnosticReportAnalyzer(DiagnosticReportReader diagnosticReportReader) {
        this.diagnosticReportReader = diagnosticReportReader;
    }

    public int calculatePowerConsumption(String reportFileName) throws DiagnosticReportAnalyzerException {
        logger.traceEntry("Calculate Power consumption for report in file \"{}\"", reportFileName);

        var diagnosticReport = readDiagnosticReport(reportFileName);
        var powerConsumption = calculatePowerConsumptionFromReport(diagnosticReport);

        return logger.traceExit(powerConsumption);
    }

    public int calculateLifeSupportRating(String reportFileName) throws DiagnosticReportAnalyzerException {
        logger.traceEntry("Calculate Life support rating for report in file \"{}\"", reportFileName);

        var diagnosticReport = readDiagnosticReport(reportFileName);

        try {
            var oxygenGeneratorRating = calculateOxygenGeneratorRating(diagnosticReport);
            var co2ScrubberRating = calculateCO2ScrubberRating(diagnosticReport);

            var lifeSupportRating = oxygenGeneratorRating * co2ScrubberRating;
            logger.info("Life support rating: {} x {} = {}", oxygenGeneratorRating, co2ScrubberRating, lifeSupportRating);

            return logger.traceExit(lifeSupportRating);
        } catch (LifeSupportCalculatorException e) {
            throw new DiagnosticReportAnalyzerException("Error calculating the life support rating", e);
        }
    }

    private int calculateOxygenGeneratorRating(DiagnosticReport diagnosticReport) throws LifeSupportCalculatorException {
        final var factory = LifeSupportCalculatorFactory.getInstance();
        var oxygenGeneratorRatingCalculator = factory.createOxygenGeneratorRating(diagnosticReport);
        return oxygenGeneratorRatingCalculator.calculate();
    }

    private int calculateCO2ScrubberRating(DiagnosticReport diagnosticReport) throws LifeSupportCalculatorException {
        final var factory = LifeSupportCalculatorFactory.getInstance();
        var co2ScrubberRatingCalculator = factory.createCO2ScrubberRating(diagnosticReport);
        return co2ScrubberRatingCalculator.calculate();
    }

    private DiagnosticReport readDiagnosticReport(String reportFileName) throws DiagnosticReportAnalyzerException {
        try {
            return diagnosticReportReader.read(reportFileName);
        } catch (DiagnosticReportReaderException e) {
            throw new DiagnosticReportAnalyzerException("Error analyzing the report", e);
        }
    }

    private int calculatePowerConsumptionFromReport(DiagnosticReport diagnosticReport) {
        logger.traceEntry();

        calculateCount1s(diagnosticReport);
        BitSet bitSet = calculateMostCommonBits(diagnosticReport);

        return logger.traceExit(getPowerConsumption(bitSet, diagnosticReport.getLineWidth()));
    }

    private void calculateCount1s(DiagnosticReport diagnosticReport) {
        var lineLength = diagnosticReport.getLineWidth();
        count1s = new int[lineLength];
        Arrays.fill(count1s, 0);

        logger.debug("lineLength = {}", lineLength);

        for (var line : diagnosticReport) {
            processDiagnosticReportLine(line);
        }

        logger.debug("count1s = {}", () -> Arrays.toString(count1s));
    }

    private void processDiagnosticReportLine(BitSet line) {
        for (int i = 0; i < line.length(); i++) {
            count1s[i] += line.get(i) ? 1 : 0;
        }
    }

    private BitSet calculateMostCommonBits(DiagnosticReport diagnosticReport) {
        int halfNumBits = diagnosticReport.getSize() / 2;
        var lineLength = diagnosticReport.getLineWidth();
        BitSet bitSet = new BitSet(lineLength);
        for (int i = 0; i < count1s.length; i++) {
            if (count1s[i] > halfNumBits) {
                bitSet.set(i);
            }
        }

        logger.debug("Most commons bits: {}", () -> fromBitSetToString(bitSet, lineLength));
        logger.debug("Most commons bits (bits to 1): {}", bitSet);

        return bitSet;
    }

    private int getPowerConsumption(BitSet bitSet, int length) {
        var gammaRate = fromBitSetToInt(bitSet, length);
        bitSet.flip(0, length);
        var epsilonRate = fromBitSetToInt(bitSet, length);
        var powerConsumption = gammaRate * epsilonRate;

        logger.info("Power consumption = {}*{}={}", gammaRate,epsilonRate, powerConsumption);
        return powerConsumption;
    }

    public static class Builder {
        private final DiagnosticReportReader diagnosticReportReader;

        public static Builder createDiagnosticReportAnalyzer() {
            var diagnosticReportReader = createDiagnosticReportReader();
            return new Builder(diagnosticReportReader);
        }

        public static Builder createDiagnosticReportAnalyzer(Path alternateBasePath) {
            var diagnosticReportReader = createDiagnosticReportReader(alternateBasePath);
            return new Builder(diagnosticReportReader);
        }

        public Builder(DiagnosticReportReader diagnosticReportReader) {
            this.diagnosticReportReader = diagnosticReportReader;
        }

        public DiagnosticReportAnalyzer build() {
            return new DiagnosticReportAnalyzer(diagnosticReportReader);
        }
    }
}
