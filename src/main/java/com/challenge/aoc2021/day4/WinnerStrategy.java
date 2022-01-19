package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.Bingo.DrawingResult;

public class WinnerStrategy extends AbstractPlayStrategy {

    public WinnerStrategy(Bingo bingo) {
        super(bingo);
    }

    @Override
    protected boolean isFinished(DrawingResult result) {
        return result.areWinnersFound() || result.allNumbersDrawn();
    }
}
