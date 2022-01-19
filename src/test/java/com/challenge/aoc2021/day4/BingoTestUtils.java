package com.challenge.aoc2021.day4;

import com.challenge.aoc2021.day4.exception.BingoBoardException;

import java.util.ArrayList;
import java.util.List;

import static com.challenge.aoc2021.day4.BingoBoard.Builder.createBingoBoard;

class BingoTestUtils {
     static List<BingoBoard> createBingoBoardsFromArray(int[][] rawData) throws BingoBoardException {
        List<BingoBoard> boards = new ArrayList<>();
        for (var boardNumbers : rawData) {
            boards.add(createBingoBoard(boardNumbers).build());
        }
        return boards;
    }
}
