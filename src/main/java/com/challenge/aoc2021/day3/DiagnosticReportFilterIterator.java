package com.challenge.aoc2021.day3;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DiagnosticReportFilterIterator implements Iterator<BitSet> {
    private DiagnosticReportFilter filter;
    private int index;
    private int lastIndex;

    DiagnosticReportFilterIterator(DiagnosticReportFilter filter) {
        this.filter = filter;
        index = filter.getFirstActiveLineIndex();
        lastIndex = -1;
    }

    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public BitSet next() {
        if (index == -1) {
            throw new NoSuchElementException(String.format("No more elements in the filter after getting element at position %d", lastIndex));
        }

        var next = filter.getLine(index);
        lastIndex = index;
        index = filter.getNextActiveLineIndex(index);
        return next;
    }

    @Override
    public void remove() {
        if (lastIndex == -1) {
            throw new IllegalStateException("Next method should be called prior calling remove");
        }

        filter.filterLine(lastIndex);
    }
}
