package com.challenge.aoc2021.day3;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
        if (index < 0) {
            throw new IllegalArgumentException(String.format("Wrong index %d: the index must be >= 0", index));
        }

        if (index >= lineBits.size()) {
            throw new IllegalArgumentException(String.format("Wrong index %d. Max allowed: %d", index, lineBits.size()-1));
        }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiagnosticReport bitSets = (DiagnosticReport) o;
        return lineBits.equals(bitSets.lineBits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineBits);
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        for (var line : lineBits) {
            s.append(bitSetToString(line) + '\n');
        }
        return s.toString();
    }

    private String bitSetToString(BitSet bitSet) {
        var s = new StringBuilder();
        for (int i = 0; i < lineWidth; i++) {
            if (bitSet.get(i)) {
                s.append('1');
            } else {
                s.append('0');
            }
        }
        return s.toString();
    }
}
