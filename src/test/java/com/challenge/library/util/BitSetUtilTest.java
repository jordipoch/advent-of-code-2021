package com.challenge.library.util;

import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BitSetUtilTest {
    @Test
    public void testConversion() {
        var originalString = "100110010100";
        var bitSet = BitSetUtil.fromStringToBitSet(originalString);
        var convertedString = BitSetUtil.fromBitSetToString(bitSet, originalString.length());

        assertThat(convertedString)
                .as("Checking that converted string ({}) is the same as expected...", convertedString)
                .isEqualTo(originalString);

        var value = BitSetUtil.fromBitSetToInt(bitSet, originalString.length());
        var expectedValue = Integer.parseInt(originalString, 2);
        assertThat(value).as("Checking converted bitSet to int ({})...", value).isEqualTo(expectedValue);
    }
}