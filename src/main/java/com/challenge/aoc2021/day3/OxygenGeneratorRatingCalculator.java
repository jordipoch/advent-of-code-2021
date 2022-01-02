package com.challenge.aoc2021.day3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.BitSet;

public class OxygenGeneratorRatingCalculator extends LifeSupportCalculator {
    private static final Logger logger = LogManager.getLogger();

    public OxygenGeneratorRatingCalculator(DiagnosticReport diagnosticReport) {
        super(diagnosticReport);
    }

    @Override
    protected String getCalculationName() {
        return "Oxygen generator rating";
    }

    @Override
    protected char getFilterValue(int bitNum) {
        int count1s = 0;
        for (BitSet bitSet : filter) {
            count1s += bitSet.get(bitNum) ? 1 : 0;
        }
        final var halfLinesCount = (int) Math.ceil(((double) filter.getNumActiveLines()) / 2);
        boolean is1MostCommonBit = count1s >= halfLinesCount;
        var filterValue = is1MostCommonBit ? '1' : '0';
        var mostCommonBitCount = is1MostCommonBit ? count1s : filter.getNumActiveLines() - count1s;
        logger.debug("Most common bit for position {}: {} ({} times)", bitNum, filterValue, mostCommonBitCount);
        return filterValue;
    }

}
