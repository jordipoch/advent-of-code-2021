package com.challenge.aoc2021.day4;

public class PlayStrategyFactory {
    private static final PlayStrategyFactory INSTANCE = new PlayStrategyFactory();

    public static PlayStrategyFactory getInstance() {
        return INSTANCE;
    }

    public PlayStrategy createPlayStrategy(PlayMode playMode, Bingo bingo) {
        return switch (playMode) {
            case WINNER -> new WinnerStrategy(bingo);
            case LOSER -> new LoserStrategy(bingo);
        };
    }
}
