package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoPlayException;

public interface PlayStrategy {
    int play() throws BingoPlayException;
}

