package com.challenge.library.util;

import org.apache.commons.lang3.StringUtils;

import java.util.BitSet;

import static java.lang.Long.parseLong;

public class BitSetUtil {
    private BitSetUtil() {
    }

    public static BitSet fromStringToBitSet(String bits) {
        return BitSet.valueOf(new long[] {parseLong(StringUtils.reverse(bits), 2)});
    }

    public static String fromBitSetToString(BitSet bitSet, int length) {
        var sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(bitSet.get(i) ? '1' : '0');
        }

        return sb.toString();
    }

    public static int fromBitSetToInt(BitSet bitSet, int length) {
        var bits = fromBitSetToString(bitSet, length);
        return Integer.parseInt(bits, 2);
    }

    public static char getBitAsCharAtPos(BitSet bitSet, int pos) {
        return bitSet.get(pos) ? '1' : '0';
    }
}
