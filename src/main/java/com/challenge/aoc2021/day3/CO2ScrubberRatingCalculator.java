package com.challenge.aoc2021.day3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.BitSet;

public class CO2ScrubberRatingCalculator extends LifeSupportCalculator {
    private static final Logger logger = LogManager.getLogger();

    public CO2ScrubberRatingCalculator(DiagnosticReport diagnosticReport) {
        super(diagnosticReport);
    }

    @Override
    protected String getCalculationName() {
        return "CO2 scrubber rating";
    }

    @Override
    protected char getFilterValue(int bitNum) {
        int count0s = 0;
        for (BitSet bitSet : filter) {
            count0s += !bitSet.get(bitNum) ? 1 : 0;
        }
        //final var halfLinesCount = Math.ceil(((double) filter.getNumActiveLines()) / 2);
        final var halfLinesCount = filter.getNumActiveLines() / 2;
        boolean is0LeastCommonBit = count0s <= halfLinesCount;
        var filterValue = is0LeastCommonBit ? '0' : '1';
        var leastCommonBitCount = is0LeastCommonBit ? count0s : filter.getNumActiveLines() - count0s;
        logger.debug("Least common bit for position {}: {} ({} times)", bitNum, filterValue, leastCommonBitCount);
        return filterValue;
    }

}
