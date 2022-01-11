package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemException;
import org.assertj.core.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.setPrintAssertionsDescription;

public class Day4Test {
    @BeforeMethod
    public void setUp() {
        setPrintAssertionsDescription(true);
    }

    @Test
    public void testRunPart1() throws BingoSubsystemException {
        final var score = Day4.runPart1();
        Assertions.assertThat(score).as("Checking result for day 4 part 1 challenge...").isEqualTo(74320);
    }
}