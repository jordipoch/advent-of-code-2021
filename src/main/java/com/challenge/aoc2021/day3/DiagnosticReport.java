package com.challenge.aoc2021.day3;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class DiagnosticReport implements Iterable<BitSet> {
    private final List<BitSet> lineBits = new ArrayList<>();
    private final int lineWidth;

    public DiagnosticReport(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void addLine(BitSet line) {
        lineBits.add(line);
    }

    public BitSet getLine(int index) {
        return lineBits.get(index);
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public int getSize() {
        return lineBits.size();
    }

    @Override
    public Iterator iterator() {
        return lineBits.iterator();
    }
}
