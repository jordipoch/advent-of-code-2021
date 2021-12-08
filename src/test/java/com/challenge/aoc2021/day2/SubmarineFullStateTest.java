package com.challenge.aoc2021.day2;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.challenge.aoc2021.day2.Direction.DOWN;
import static com.challenge.aoc2021.day2.Direction.FORWARD;
import static com.challenge.aoc2021.day2.Direction.UP;
import static com.challenge.aoc2021.day2.SubmarineFullState.of;
import static org.testng.Assert.assertEquals;

public class SubmarineFullStateTest {

    @Test (dataProvider = "simpleMovesTestData")
    public void testSimpleMoves(Direction direction, int amount, SubmarineState expectedState) {
        SubmarineState submarineState = SubmarineStateFactory.getInstance().createSubmarineFullState();
        SubmarineState newSubmarineState = submarineState.move(direction, amount);

        assertEquals(newSubmarineState, expectedState);
    }

    @Test (dataProvider = "compoundMovesTestData")
    public void testCompoundMoves(List<Move> moves, SubmarineState expectedState) {
        SubmarineState newSubmarineState = SubmarineStateFactory.getInstance().createSubmarineFullState();
        for (Move move : moves) {
            newSubmarineState = newSubmarineState.move(move.getDirection(), move.getAmount());
        }

        assertEquals(newSubmarineState, expectedState);
    }

    @DataProvider(name = "simpleMovesTestData")
    private Iterator<Object[]> getSimpleMovesTestData() {
        return Arrays.asList(new Object[][] {
                {FORWARD, 3, of(3, 0, 0)},
                {DOWN, 5, of(0, 0, 0)},
                {UP, 2, of(0, 0, 0)}
        }).iterator();
    }

    @DataProvider(name = "compoundMovesTestData")
    private Iterator<Object[]> getCompoundMovesTestData() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList(Move.of(FORWARD, 3), Move.of(DOWN, 5)),
                        of(3, 0, 0)},
                {Arrays.asList(Move.of(DOWN, 3), Move.of(FORWARD, 7), Move.of(UP, 1)),
                        of(7, 21, 0)},
                {Arrays.asList(Move.of(DOWN, 3), Move.of(FORWARD, 4),
                        Move.of(FORWARD, 3), Move.of(DOWN, 4),
                        Move.of(UP, 5), Move.of(DOWN, 10),
                        Move.of(FORWARD, 7), Move.of(DOWN, 6)),
                        of(14, 105, 6)}
        }).iterator();
    }

}