package com.challenge.aoc2021.day1;

import com.challenge.aoc2021.day1.exception.DepthScannerCreationException;
import com.challenge.library.files.TextFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DepthScanner {
    private static final Logger logger = LogManager.getLogger();
    private static final Path BASE_PATH = Paths.get("src","main", "resources", "com", "challenge", "day1");
    private static final int WINDOW_SIZE = 3;
    private final int[] depthMeasurements;

    public static DepthScanner createDepthScanner(int[] depthMeasurements) {
        return new DepthScanner(depthMeasurements);
    }

    public static DepthScanner createDepthScanner(String inputFile) throws DepthScannerCreationException {
        List<String> lines;
        try {
            lines = TextFileReader.readAllLinesFromFile(BASE_PATH.resolve(inputFile));
        } catch (IOException e) {
            throw new DepthScannerCreationException("Cannot create DepthScanner", e);
        }
        int[] depthMeasurements = parseMeasurements(lines);

        return createDepthScanner(depthMeasurements);
    }

    private DepthScanner(int[] depthMeasurements) {
        this.depthMeasurements = depthMeasurements;
    }

    private static int[] parseMeasurements(List<String> measurementStrings) throws DepthScannerCreationException {
        int[] measurements = new int[measurementStrings.size()];

        for (int i = 0; i < measurementStrings.size(); i++) {
            try {
                measurements[i] = Integer.parseInt(measurementStrings.get(i));
            } catch (NumberFormatException e) {
                throw new DepthScannerCreationException(String.format("Error parsing measurement number %d: %s", i, measurementStrings.get(i)), e);
            }
        }

        return measurements;
    }

    public int getNumIncreases() {
        logger.traceEntry();

        int numIncreases = 0;
        for (int i = 1; i < depthMeasurements.length; i++) {
            if (depthMeasurements[i] > depthMeasurements[i-1]) {
                numIncreases++;
            }
        }

        return logger.traceExit(numIncreases);
    }

    public int getNumWindowIncreases() {
        logger.traceEntry();

        if (depthMeasurements.length < WINDOW_SIZE + 1) {
            return 0;
        }

        int numIncreases = 0;
        for (int i = WINDOW_SIZE; i < depthMeasurements.length; i++) {
            // Current and previous window have the same measurements except for the first and last ones
            // So, it's enough comparing the last measurement from the current window to the first measurement from the last one
            if (depthMeasurements[i] > depthMeasurements[i-WINDOW_SIZE]) {
                numIncreases++;
                logger.debug("{} > {}", depthMeasurements[i], depthMeasurements[i-WINDOW_SIZE]);
            }
        }

        return logger.traceExit(numIncreases);
    }
}