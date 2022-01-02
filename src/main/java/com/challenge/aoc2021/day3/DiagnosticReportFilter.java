package com.challenge.aoc2021.day3;

import com.challenge.library.util.BitSetUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.BitSet;

public class DiagnosticReportFilter implements Iterable<BitSet> {
    private final DiagnosticReport diagnosticReport;
    private final BitSet activeLines;
    private final int width;
    private final int numLines;
    private final String emptyLine;

    public DiagnosticReportFilter(DiagnosticReport diagnosticReport) {
        this.diagnosticReport = diagnosticReport;
        width = diagnosticReport.getLineWidth();
        numLines = diagnosticReport.getSize();
        activeLines = new BitSet();
        emptyLine = StringUtils.repeat('-', width);
        initFilter();
    }

    private void initFilter() {
        for (int i = 0; i < numLines; i++) {
            activeLines.set(i);
        }
    }

    @Override
    public DiagnosticReportFilterIterator iterator() {
        return new DiagnosticReportFilterIterator(this);
    }

    /**
     * @return the index of the first active line
     */
    public int getFirstActiveLineIndex() {
        return activeLines.nextSetBit(0);
    }

    /**
     * Get the next active line starting from a position
     * @param fromPosition initial position (excluding)
     * @return the index of the next active line or -1 if there's no active line from that position
     */
    public int getNextActiveLineIndex(int fromPosition) {
        return activeLines.nextSetBit(fromPosition + 1);
    }

    /**
     * Returns a line at the given position
     * @param line
     * @return The line at the given position
     * @throws IllegalArgumentException if the line at position is filtered out or if the position is outside the range
     */
    public BitSet getLine(int line) {
        checkIndex(line);

        return diagnosticReport.getLine(line);
    }

    /**
     * Filter a line of the report
     * @param line
     * @return the number of active lines left
     * @throws IllegalStateException if the line is already filtered
     * @throws IllegalArgumentException if the line is outside the bounds of the report
     */
    public int filterLine(int line) {
        checkIndex(line);
        activeLines.clear(line);
        return activeLines.cardinality();
    }

    public int getNumActiveLines() {
        return activeLines.cardinality();
    }

    public int getLineWidth() {
        return width;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= numLines) {
            throw new IllegalArgumentException(String.format("Position %d outside the range (0, %d)", index, numLines-1));
        }

        if (!activeLines.get(index)) {
            throw new IllegalArgumentException(String.format("Position %d is filtered out (active lines = %s)", index, activeLines));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numLines; i++) {
            if (activeLines.get(i)) {
                sb.append(BitSetUtil.fromBitSetToString(diagnosticReport.getLine(i), width));
            } else {
                sb.append(emptyLine);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
