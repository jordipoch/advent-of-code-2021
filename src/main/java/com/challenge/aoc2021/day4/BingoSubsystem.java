package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemException;
import com.challenge.aoc2021.day4.exception.BingoSubsystemReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BingoSubsystem {
    private static final Logger logger = LogManager.getLogger();

    private final List<Integer> numbersToDraw;
    private final List<BingoBoard> boards;

    public BingoSubsystem(List<Integer> numbersToDraw, List<BingoBoard> boards) {
        this.numbersToDraw = numbersToDraw;
        this.boards = boards;
    }

    public int calculateScoreOfWinningBoard() throws BingoSubsystemException {
        logger.traceEntry();
        for (int number : numbersToDraw) {
            var bingoBoardOptional = markNumber(number);
            if (bingoBoardOptional.isPresent()) {
                final var winningBoard = bingoBoardOptional.get();
                final var score = winningBoard.getSumOfUnmarkedNumbers() * number;

                logger.info("Found a winning board with score {} after drawing number {}:\n{}", score, number, winningBoard);
                return logger.traceExit(score);
            }
        }

        logger.warn("No winning board after drawing all the numbers!");

        return logger.traceExit(0);
    }

    private Optional<BingoBoard> markNumber(int number) throws BingoSubsystemException {
        logger.traceEntry("Mark number {} on the boards...", number);

        BingoBoard winningBoard = null;
        for (var board : boards) {
            if (board.markIfExists(number)) {
                if (winningBoard != null) {
                    throw new BingoSubsystemException(String.format("Found more than one winning board after drawing number %d", number));
                } else {
                    winningBoard = board;
                }
            }
        }

        return logger.traceExit(Optional.ofNullable(winningBoard));
    }

    public static class Builder {
        private String filename;
        private boolean isTest = false;

        public static Builder createBingoSubsystem() {
            return new Builder();
        }

        public Builder fromFile(String filename) {
            this.filename = filename;
            return this;
        }

        public Builder forTest() {
            isTest = true;
            return this;
        }

        public BingoSubsystem build() throws BingoSubsystemException {
            final BingoSubsystemReader reader;
            if (isTest) {
                reader = BingoSubsystemReader.createForTest();
            } else {
                reader = BingoSubsystemReader.create();
            }

            try {
                final var components = reader.read(filename);
                return new BingoSubsystem(components.getNumbersToDraw(), components.getBoards());
            } catch (BingoSubsystemReaderException e) {
                throw new BingoSubsystemException("Error building the bingo subsystem", e);
            }

        }

    }

    public static class Components {
        private List<Integer> numbersToDraw;
        private List<BingoBoard> boards;

        public List<Integer> getNumbersToDraw() {
            return numbersToDraw;
        }

        public void setNumbersToDraw(List<Integer> numbersToDraw) {
            this.numbersToDraw = numbersToDraw;
        }

        public List<BingoBoard> getBoards() {
            return boards;
        }

        public void setBoards(List<BingoBoard> boards) {
            this.boards = boards;
        }
    }
}
