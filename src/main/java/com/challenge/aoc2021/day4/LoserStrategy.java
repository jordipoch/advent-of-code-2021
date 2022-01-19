package com.challenge.aoc2021.day4;

public class LoserStrategy extends AbstractPlayStrategy {

    public LoserStrategy(Bingo bingo) {
        super(bingo);
    }

    @Override
    protected boolean isFinished(Bingo.DrawingResult result) {
        return result.areLastWinnersFound() || result.allNumbersDrawn();
    }
}
