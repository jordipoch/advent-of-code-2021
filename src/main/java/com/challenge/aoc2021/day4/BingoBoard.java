package com.challenge.aoc2021.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.challenge.aoc2021.day4.exception.BingoBoardException;
import com.challenge.library.util.Coord2D;

public class BingoBoard {
    private final int size;
    private final Map<Coord2D, BoardNumber> boardContent;
    private final Map<Integer, Coord2D> numberPositions;

    public BingoBoard(Map<Coord2D, BoardNumber> boardContent, Map<Integer, Coord2D> numberPositions, int size) {
        this.boardContent = boardContent;
        this.numberPositions = numberPositions;
        this.size = size;
    }

    public boolean markIfExists(int number) {
        getBoardNumber(number).ifPresent(BoardNumber::mark);
        if (isNumberMarked(number)) {
            return checkIfLineExists(numberPositions.get(number));
        }

        return false;
    }

    public int getNumberAtPosition(int x, int y) {
        return boardContent.get(Coord2D.of(x, y)).getNumber();
    }

    public boolean isNumberMarked(int number) {
        var boardNumber = getBoardNumber(number);
        return boardNumber.map(BoardNumber::isMarked).orElse(false);

    }

    public int getSumOfUnmarkedNumbers() {
        return boardContent.values().stream()
                .filter(boardNumber -> !boardNumber.isMarked())
                .mapToInt(BoardNumber::getNumber)
                .sum();
    }

    public int getSize() {
        return size;
    }

    private Optional<BoardNumber> getBoardNumber(int number) {
        var coord = numberPositions.get(number);
        var boardNumber = boardContent.get(coord);

        return Optional.ofNullable(boardNumber);
    }

    private boolean checkIfLineExists(Coord2D coord) {
        return theresAFullRow(coord.getY()) || theresAFullColumn(coord.getX());
    }

    private boolean theresAFullRow(int row) {
        return getRowStream(row).allMatch(BoardNumber::isMarked);
    }

    private boolean theresAFullColumn(int column) {
        return getColumnStream(column).allMatch(BoardNumber::isMarked);
    }

    private Stream<BoardNumber> getRowStream(int row) {
        var builder = Stream.<BoardNumber>builder();
        for (int i = 0; i < size; i++) {
            builder.add(boardContent.get(Coord2D.of(i, row)));
        }
        return builder.build();
    }

    private Stream<BoardNumber> getColumnStream(int column) {
        var builder = Stream.<BoardNumber>builder();
        for (int i = 0; i < size; i++) {
            builder.add(boardContent.get(Coord2D.of(column, i)));
        }
        return builder.build();
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                var coord = Coord2D.of(j, i);
                var boardNumber = boardContent.get(coord);
                sb.append(String.format("%4s", (boardNumber.isMarked() ? "X" : "") + boardNumber.getNumber()));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BingoBoard that = (BingoBoard) o;
        return size == that.size &&
                boardContent.equals(that.boardContent) &&
                numberPositions.equals(that.numberPositions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, boardContent, numberPositions);
    }

    private static class BoardNumber {
        private final int number;
        private boolean marked = false;

        private BoardNumber(int number) {
            this.number = number;
        }

        public static BoardNumber of(int number) {
            return new BoardNumber(number);
        }

        public int getNumber() {
            return number;
        }

        public boolean isMarked() {
            return marked;
        }

        public void mark() {
            marked = true;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BoardNumber that = (BoardNumber) o;
            return number == that.number && marked == that.marked;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number, marked);
        }
    }

    public static class Builder {
        private final Map<Coord2D, BoardNumber> boardContent = new HashMap<>();
        private final Map<Integer, Coord2D> numberPositions = new HashMap<>();

        private final List<Integer> numbers;
        private final int size;

        public static Builder createBingoBoard(int[] numbers) throws BingoBoardException {
            return createBingoBoard(Arrays.stream(numbers).boxed().collect(Collectors.toList()));
        }

        public static Builder createBingoBoard(List<Integer> numbers) throws BingoBoardException {
            return new Builder(numbers);
        }

        public Builder(List<Integer> numbers) throws BingoBoardException {
            this.numbers = numbers;
            size = getAndCheckSize(numbers);
        }

        private int getAndCheckSize(List<Integer> numbers) throws BingoBoardException {
            var doubleSize = Math.sqrt(numbers.size());
            if (Math.rint(doubleSize) != doubleSize) {
                throw new BingoBoardException(numbers.size());
            }
            return (int) doubleSize;
        }

        public BingoBoard build() {
            createBoardMaps();

            return new BingoBoard(boardContent, numberPositions, size);
        }

        private void createBoardMaps() {
            var x = 0;
            var y = 0;
            for (var number : numbers) {
                var coord = Coord2D.of(x, y);
                var boardNumber = BoardNumber.of(number);
                boardContent.put(coord, boardNumber);
                numberPositions.put(number, coord);
                x++;
                if (x == size) {
                    x = 0;
                    y++;
                }
            }
        }
    }
}
