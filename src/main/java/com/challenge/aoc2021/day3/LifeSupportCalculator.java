package com.challenge.aoc2021.day3;

import com.challenge.aoc2021.day3.exception.LifeSupportCalculatorException;
import com.challenge.aoc2021.day3.exception.WrongFilterStatusException;
import com.challenge.library.util.BitSetUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.BitSet;

import static com.challenge.library.util.BitSetUtil.getBitAsCharAtPos;

public abstract class LifeSupportCalculator {
    private static final Logger logger = LogManager.getLogger();
    protected final DiagnosticReportFilter filter;

    public LifeSupportCalculator(DiagnosticReport diagnosticReport) {
        this.filter = new DiagnosticReportFilter(diagnosticReport);
    }

    public int calculate() throws LifeSupportCalculatorException {
        logger.traceEntry("Calculating {}... (report size = {}x{})",
                getCalculationName(), filter.getLineWidth(), filter.getNumActiveLines());

        filterLines();
        int result;
        try {
            result = getResult();
        } catch (WrongFilterStatusException e) {
            throw new LifeSupportCalculatorException(String.format("Error while calculating the %s", getCalculationName()), e);
        }

        return logger.traceExit(result);
    }

    protected abstract String getCalculationName();

    private void filterLines() {
        int bitNum = 0;
        while (filter.getNumActiveLines() > 1 && bitNum < filter.getLineWidth()) {
            filterLinesPerBitNum(bitNum);
            logger.debug("{} active lines after filtering by pos {}", filter.getNumActiveLines(), bitNum);
            logger.trace("Filter contents:{}{}", System::lineSeparator, () -> filter);
            if (filter.getNumActiveLines() > 1) {
                bitNum++;
            }
        }
    }

    private int getResult() throws WrongFilterStatusException {
        check1LineLeft();

        BitSet remainingLine = filter.iterator().next();
        logger.debug("RemainingLine: {}", () -> BitSetUtil.fromBitSetToString(remainingLine, filter.getLineWidth()));
        int result = BitSetUtil.fromBitSetToInt(remainingLine, filter.getLineWidth());
        logger.info("{} = {}", this::getCalculationName, () -> result);
        return result;
    }

    private void check1LineLeft() throws WrongFilterStatusException {
        if (filter.getNumActiveLines() > 1) {
            throw new WrongFilterStatusException(filter);
        }
    }

    private void filterLinesPerBitNum(int bitNum) {
        logger.traceEntry("Filtering report by bit num {}...", bitNum);

        char filterValue = getFilterValue(bitNum);
        filter(bitNum, filterValue);

        logger.traceExit("{} lines left after filtering", filter.getNumActiveLines());
    }

    protected abstract char getFilterValue(int bitNum);

    private void filter(int bitNum, char filterValue) {
        DiagnosticReportFilterIterator iterator;
        iterator = filter.iterator();

        while (iterator.hasNext()) {
            BitSet line = iterator.next();
            if (getBitAsCharAtPos(line, bitNum) != filterValue) {
                iterator.remove();
            }
        }
    }
}
