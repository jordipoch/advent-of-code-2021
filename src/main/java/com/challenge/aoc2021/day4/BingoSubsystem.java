package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoPlayException;

public class BingoSubsystem {
    private final PlayStrategy playStrategy;

    public BingoSubsystem(PlayStrategy playStrategy) {
        this.playStrategy = playStrategy;
    }

    public int play() throws BingoPlayException {
        return playStrategy.play();
    }

    public static class Builder {
        private PlayStrategy playStrategy;

        public static Builder createBingoSubsystem() {
            return new Builder();
        }

        public Builder withPlayStrategy(PlayStrategy playStrategy) {
            this.playStrategy = playStrategy;
            return this;
        }

        public BingoSubsystem build() {
            return new BingoSubsystem(playStrategy);
        }
    }
}
