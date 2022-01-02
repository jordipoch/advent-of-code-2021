package com.challenge.aoc2021.day3;

import org.apache.commons.lang3.StringUtils;

import java.util.BitSet;

import static java.lang.Long.parseLong;

public class BitSetTestBuilder {
    private BitSetTestBuilder() {}

    public static BitSet of(String bits) {
        return BitSet.valueOf(new long[] {parseLong(StringUtils.reverse(bits), 2)});
    }
}
