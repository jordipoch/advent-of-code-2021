package com.challenge.aoc2021.day2;

import com.challenge.aoc2021.day2.exception.InstructionsFileReaderException;
import com.challenge.aoc2021.day2.exception.SubmarineControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SubmarineController {
    private static final Logger logger = LogManager.getLogger();


    private SubmarineState submarineState;
    private final List<Move> instructions;

    private SubmarineController(List<Move> instructions, SubmarineState submarineState) {
        this.submarineState = submarineState;
        this.instructions = instructions;
    }

    public int pilotSubmarine() {
        logger.traceEntry();

        for (var instruction : instructions) {
            submarineState = submarineState.move(instruction.getDirection(), instruction.getAmount());
        }

        logger.debug("Submarine state after piloting: {}", submarineState);
        var result = submarineState.getPosition() * submarineState.getDepth();
        logger.debug("Result after piloting submarine: {}", result);

        return logger.traceExit(result);
    }

    public static class Builder {
        private InstructionsFileReader instructionsFileReader = null;
        private final String instructionsFile;
        private SubmarineState submarineState;

        public static Builder createSubmarineController(String instructionsFile) {
            return new Builder(instructionsFile);
        }

        public Builder(String instructionsFile) {
            this.instructionsFile = instructionsFile;
        }

        public Builder withInstructionsFileReader(InstructionsFileReader instructionsFileReader) {
            this.instructionsFileReader = instructionsFileReader;
            return this;
        }

        public Builder withFullState() {
            this.submarineState = SubmarineStateFactory.getInstance().createSubmarineFullState();
            return this;
        }

        public SubmarineController build() throws SubmarineControllerException {
            if (instructionsFileReader == null) {
                instructionsFileReader = new InstructionsFileReader();
            }

            if (submarineState == null) {
                this.submarineState = SubmarineStateFactory.getInstance().createSubmarineSimpleState();
            }

            var instructions = readInstructions();

            return new SubmarineController(instructions, submarineState);
        }

        private List<Move> readInstructions() throws SubmarineControllerException {
            try {
                return instructionsFileReader.readInstructions(instructionsFile);
            } catch (InstructionsFileReaderException e) {
                throw new SubmarineControllerException("Cannot read submarine instructions", e);
            }
        }
    }
}
