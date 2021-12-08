package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.SubmarineControllerException;
import org.testng.annotations.Test;

import static com.challenge.aoc2021.day2.SubmarineController.Builder.createSubmarineController;
import static org.testng.Assert.*;

public class SubmarineControllerTest {

    @Test
    public void testPilotSubmarineWithSimpleState() throws SubmarineControllerException {
        SubmarineController sc = createSubmarineController("InstructionsTestData1.txt")
                .withInstructionsFileReader(new InstructionsFileReader(InstructionsFileReaderTest.BASE_PATH))
                .build();
        var result = sc.pilotSubmarine();

        assertEquals(result, 150);
    }

    @Test
    public void testPilotSubmarineWithFullState() throws SubmarineControllerException {
        SubmarineController sc = createSubmarineController("InstructionsTestData1.txt")
                .withInstructionsFileReader(new InstructionsFileReader(InstructionsFileReaderTest.BASE_PATH))
                .withFullState()
                .build();
        var result = sc.pilotSubmarine();

        assertEquals(result, 900);
    }
}