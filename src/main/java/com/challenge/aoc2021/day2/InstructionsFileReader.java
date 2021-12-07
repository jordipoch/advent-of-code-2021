package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.InstructionsFileReaderException;
import com.challenge.library.files.TextFileReader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InstructionsFileReader {
    private static final Path DEFAULT_BASE_PATH = Paths.get("src","main", "resources", "com", "challenge", "aoc2021", "day2");
    private final Path basePath;

    public InstructionsFileReader() {
        this(DEFAULT_BASE_PATH);
    }

    public InstructionsFileReader(Path basePath) {
        this.basePath = basePath;
    }

    public List<Move> readInstructions(String filename) throws InstructionsFileReaderException {
        var lines = readLinesFromFile(basePath.resolve(filename));
        return parseInstructions(lines);
    }

    private List<Move> parseInstructions(List<String> lines) {
        return lines.stream()
                .map(line -> line.split("\\s"))
                .filter(array -> array.length == 2)
                .map(array -> Move.of(Direction.fromString(array[0]), Integer.parseInt(array[1])))
                .collect(Collectors.toList());
    }

    private List<String> readLinesFromFile(Path filePath) throws InstructionsFileReaderException {
        try {
            return TextFileReader.readAllLinesFromFile(filePath);
        } catch (IOException e) {
            throw new InstructionsFileReaderException("Can't read from " + filePath.toString(), e);
        }
    }
}
