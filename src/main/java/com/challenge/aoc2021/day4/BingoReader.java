package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoBoardException;
import com.challenge.aoc2021.day4.exception.BingoSubsystemReaderException;
import com.challenge.library.files.TextFileReader;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.challenge.aoc2021.day4.BingoBoard.Builder.createBingoBoard;

public class BingoReader {

    private static final Path DEFAULT_BASE_PATH = Paths.get("src", "main");
    private static final Path DEFAULT_BASE_TEST_PATH = Paths.get("src", "test");
    private static final Path PATH = Paths.get("resources", "com", "challenge", "aoc2021", "day4");
    private final Path basePath;

    private BingoReader(Path basePath) {
        this.basePath = basePath.resolve(PATH);
    }

    public static BingoReader create() {
        return new BingoReader(DEFAULT_BASE_PATH);
    }

    public static BingoReader createForTest() {
        return new BingoReader(DEFAULT_BASE_TEST_PATH);
    }

    public Bingo read(String file) throws BingoSubsystemReaderException {
        var lines = readLines(file);

        return Bingo.Builder.createBingo()
                .withNumbersToDraw(readNumbersToDraw(lines.get(0)))
                .withBoardsToPlay(readBoards(lines))
                .build();
    }

    private List<String> readLines(String file) throws BingoSubsystemReaderException {
        var filePath = basePath.resolve(file);
        try {
            return TextFileReader.readAllLinesFromFile(filePath);
        } catch (IOException e) {
            throw new BingoSubsystemReaderException("Error reading the bingo subsystem from file", e);
        }
    }

    private List<Integer> readNumbersToDraw(String s) {
        return Arrays.stream(s.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private List<BingoBoard> readBoards(List<String> lines) throws BingoSubsystemReaderException {
        List<BingoBoard> boards = new ArrayList<>();

        ListIterator<String> iterator = lines.listIterator(1);
        Optional<BingoBoard> boardOptional;
        do {
            boardOptional = readBoard(iterator);
            boardOptional.ifPresent(boards::add);
        } while (boardOptional.isPresent());

        return boards;
    }

    private Optional<BingoBoard> readBoard(ListIterator<String> iterator) throws BingoSubsystemReaderException {
        if(!moveToNextBoard(iterator))
            return Optional.empty();

        List<Integer> numbers = readBoardNumbers(iterator);

        try {
            return Optional.of(createBingoBoard(numbers).build());
        } catch (BingoBoardException e) {
            throw new BingoSubsystemReaderException("Error reading board", e);
        }
    }

    private boolean moveToNextBoard(ListIterator<String> iterator) {
        while (iterator.hasNext()) {
            if (StringUtils.isNotEmpty(iterator.next())) {
                iterator.previous();
                return true;
            }
        }

        return false;
    }

    private List<Integer> readBoardNumbers(ListIterator<String> iterator) throws BingoSubsystemReaderException {
        var boardNumbers = new ArrayList<Integer>();
        while (iterator.hasNext()) {
            final var line = iterator.next();
            if (StringUtils.isNotEmpty(line)) {
                List<Integer> lineNumbers = parseLine(line);
                boardNumbers.addAll(lineNumbers);
            } else {
                break;
            }
        }

        return boardNumbers;
    }

    private List<Integer> parseLine(String line) throws BingoSubsystemReaderException {
        try {
            return Arrays.stream(line.trim().split("\\h+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new BingoSubsystemReaderException(String.format("Parsing error reading line: %s", line), e);
        }
    }
}