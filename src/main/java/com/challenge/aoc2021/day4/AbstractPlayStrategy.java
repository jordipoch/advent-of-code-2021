package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.Bingo.DrawingResult;
import com.challenge.aoc2021.day4.exception.BingoException;
import com.challenge.aoc2021.day4.exception.BingoPlayException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractPlayStrategy implements PlayStrategy {
    private static final Logger logger = LogManager.getLogger();

    private final Bingo bingo;

    protected AbstractPlayStrategy(Bingo bingo) {
        this.bingo = bingo;
    }

    @Override
    public int play() throws BingoPlayException {
        logger.traceEntry();
        try {
            var result = tryPlayBingo();
            return logger.traceExit(result);
        } catch (BingoException e) {
            throw new BingoPlayException("Error while playing bingo", e);
        }
    }

    private int tryPlayBingo() throws BingoException {
        logger.traceEntry();

        DrawingResult result;
        do {
            result = bingo.drawNextNumber();
        } while (!isFinished(result));

        return logger.traceExit(processResult(result));
    }

    protected int processResult(DrawingResult result) throws BingoException {
        logger.traceEntry();

        var winningBoard = getWinningBoard(result);
        var score = calculateScore(winningBoard, result.getNumberDrawn());

        logger.info("Found a winning board with score {} after drawing number {}:\n{}", score,
                result.getNumberDrawn(), result.getWinningBoards().get(0));

        return logger.traceExit(score);
    }

    private BingoBoard getWinningBoard(DrawingResult result) throws BingoException {
        if (result.areWinnersFound()) {
            var winningBoards = result.getWinningBoards();
            if (winningBoards.size() > 1) {
                throw new BingoException("More than 1 winning board found!");
            }
            return winningBoards.get(0);
        } else {
            throw new BingoException("No winning board found!");
        }
    }

    protected int calculateScore(BingoBoard bingoBoard, int lastNumberDrawn) {
        return bingoBoard.getSumOfUnmarkedNumbers() * lastNumberDrawn;
    }

    protected abstract boolean isFinished(DrawingResult result);
}
