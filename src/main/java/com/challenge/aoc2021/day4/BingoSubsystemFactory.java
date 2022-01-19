package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoSubsystemCreationException;
import com.challenge.aoc2021.day4.exception.BingoSubsystemReaderException;

import static com.challenge.aoc2021.day4.BingoSubsystem.Builder.createBingoSubsystem;

public class BingoSubsystemFactory {
    private static final BingoSubsystemFactory INSTANCE = new BingoSubsystemFactory();

    public static BingoSubsystemFactory getInstance() {
        return INSTANCE;
    }

    public BingoSubsystem create(String filename, PlayMode playMode) throws BingoSubsystemCreationException {
        try {
            var bingo = BingoReader.create().read(filename);
            return create(playMode, bingo);
        } catch (BingoSubsystemReaderException e) {
            throw new BingoSubsystemCreationException("Error creating the bingo subsystem", e);
        }
    }

    public BingoSubsystem createForTest(String filename, PlayMode playMode) throws BingoSubsystemCreationException {
        try {
            var bingo = BingoReader.createForTest().read(filename);
            return create(playMode, bingo);
        } catch (BingoSubsystemReaderException e) {
            throw new BingoSubsystemCreationException("Error creating the bingo subsystem", e);
        }
    }

    private BingoSubsystem create(PlayMode playMode, Bingo bingo) {
        return createBingoSubsystem()
                .withPlayStrategy(createPlayStrategy(playMode, bingo))
                .build();
    }

    private PlayStrategy createPlayStrategy(PlayMode playMode, Bingo bingo) {
        var factory = PlayStrategyFactory.getInstance();
        return factory.createPlayStrategy(playMode, bingo);
    }
}
