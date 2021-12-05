package com.challenge.aoc2021;

import com.challenge.aoc2021.day1.DepthScanner;
import com.challenge.aoc2021.day1.exception.DepthScannerCreationException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;

import static org.testng.Assert.*;

public class DepthScannerTest {

    @Test (dataProvider = "measurementsArrays")
    public void testGetNumIncreases_FromArrays(int[] depthMeasurements, int expectedIncreases) {
        var depthScanner = DepthScanner.createDepthScanner(depthMeasurements);
        var numIncreases = depthScanner.getNumIncreases();

        assertEquals(numIncreases, expectedIncreases);
    }

    @Test (dataProvider = "measurementsFiles")
    public void testGetNumIncreases_FromFiles(String inputFile, int expectedIncreases) throws DepthScannerCreationException {
        var depthScanner = DepthScanner.createDepthScanner(inputFile);
        var numIncreases = depthScanner.getNumIncreases();

        assertEquals(numIncreases, expectedIncreases);
    }

    @DataProvider(name = "measurementsArrays")
    private Iterator<Object[]> getMeasurementArrays() {
        return Arrays.asList(new Object[][] {
                { new int[] {0, 1, 3, 2}, 2 },
                { new int[] {0, 3, 3, 1, 2, 5, 1, 2, 4}, 5 }
        }).iterator();
    }

    @DataProvider(name = "measurementsFiles")
    private Iterator<Object[]> getMeasurementFiles() {
        return Arrays.asList(new Object[][] {
                { "inputTest1.txt", 6 },
                { "inputTest2.txt", 11 },
        }).iterator();
    }
}