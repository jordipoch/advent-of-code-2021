package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bingo {
    private static final Logger logger = LogManager.getLogger();

    private final List<Integer> numbersToDraw;
    private final List<BingoBoard> boards;
    private final List<BingoBoard> remainingBoards;

    private int numIndex = 0;

    public Bingo(List<Integer> numbersToDraw, List<BingoBoard> boards) {
        this.numbersToDraw = numbersToDraw;
        this.boards = boards;
        this.remainingBoards = new ArrayList<>(boards);
    }

    public DrawingResult drawNextNumber() throws BingoException {
        if (numIndex >= numbersToDraw.size()) {
            throw new BingoException("There are no more numbers to draw!");
        }

        final int number = numbersToDraw.get(numIndex++);
        final List<BingoBoard> winningBoards = new ArrayList<>();
        var listIterator = remainingBoards.listIterator();
        while (listIterator.hasNext()) {
            var board = listIterator.next();
            if (board.markIfExists(number)) {
                winningBoards.add(board);
                listIterator.remove();
            }
        }

        DrawingResult result;
        if (winningBoards.isEmpty()) {
            result = DrawingResult.ofNoWinningBoards(this);
        } else {
            result = DrawingResult.ofWinningBoards(this, winningBoards);
        }

        logger.debug("Drawing result: {}", result);

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Bingo bingo = (Bingo) o;

        return new EqualsBuilder().append(numbersToDraw, bingo.numbersToDraw).append(boards, bingo.boards).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(numbersToDraw).append(boards).toHashCode();
    }

    @Override
    public String toString() {
        return "BINGO: \n" +
                "numbersToDraw = " + numbersToDraw + "\n" +
                "boards:\n" + boards.stream().map(bb -> bb.toString() + "\n").collect(Collectors.joining());
    }

    public static class DrawingResult {
        private final List<BingoBoard> winningBoards = new ArrayList<>();
        private final int numbersLeftToDraw;
        private final int numRemainingBoards;
        private final int numberDrawn;

        public DrawingResult(Bingo bingo) {
            this.numbersLeftToDraw = bingo.numbersToDraw.size() - bingo.numIndex;
            this.numRemainingBoards = bingo.remainingBoards.size();
            this.numberDrawn = bingo.numbersToDraw.get(bingo.numIndex - 1);
        }

        public DrawingResult(Bingo bingo, List<BingoBoard> winningBoards) {
            this(bingo);
            this.winningBoards.addAll(winningBoards);
        }

        private static DrawingResult ofNoWinningBoards(Bingo bingo) {
            return new DrawingResult(bingo);
        }

        private static DrawingResult ofWinningBoards(Bingo bingo, List<BingoBoard> winningBoards) {
            return new DrawingResult(bingo, winningBoards);
        }

        public List<BingoBoard> getWinningBoards() {
            return winningBoards;
        }

        public int getNumbersLeftToDraw() {
            return numbersLeftToDraw;
        }

        public int getNumRemainingBoards() {
            return numRemainingBoards;
        }

        public int getNumberDrawn() {
            return numberDrawn;
        }

        public boolean areWinnersFound() {
            return !winningBoards.isEmpty();
        }

        public boolean areLastWinnersFound() {
            return !winningBoards.isEmpty() && numRemainingBoards == 0;
        }

        public boolean allNumbersDrawn() {
            return numbersLeftToDraw == 0;
        }


        @Override
        public String toString() {
            return "{ Num drawn:" + numberDrawn +
                    ", #winning boards: " + winningBoards.size() +
                    ", #remaining boards: " + numRemainingBoards +
                    ", #numbers left to draw: " + numbersLeftToDraw +
                    "}";
        }
    }

    public static class Builder {
        private List<Integer> numbersToDraw;
        private List<BingoBoard> boards;

        public static Builder createBingo() {
            return new Builder();
        }

        public Builder withNumbersToDraw(List<Integer> numbersToDraw) {
            this.numbersToDraw = numbersToDraw;
            return this;
        }

        public Builder withBoardsToPlay(List<BingoBoard> boards) {
            this.boards = boards;
            return this;
        }

        public Bingo build() {
            return new Bingo(numbersToDraw, boards);
        }
    }
}
