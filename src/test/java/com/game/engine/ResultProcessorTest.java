package com.game.engine;

import com.game.constant.Move;
import com.game.constant.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultProcessorTest {

    private ResultProcessor resultProcessor;

    @BeforeEach
    void setUp() {
        resultProcessor = new ResultProcessor();
    }

    @ParameterizedTest
    @MethodSource("getMoves")
    void testCalculateGameResult(Result expectedResult, Move playerMove, Move pcMove) {
        var actualResult = resultProcessor.calculateGameResult(playerMove, pcMove);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> getMoves() {
        return Stream.of(
                Arguments.of(Result.PLAYER_WIN, Move.PAPER, Move.ROCK),
                Arguments.of(Result.PC_WIN, Move.SCISSORS, Move.ROCK),
                Arguments.of(Result.DRAW, Move.ROCK, Move.ROCK),

                Arguments.of(Result.PLAYER_WIN, Move.SCISSORS, Move.PAPER),
                Arguments.of(Result.PC_WIN, Move.ROCK, Move.PAPER),
                Arguments.of(Result.DRAW, Move.PAPER, Move.PAPER),

                Arguments.of(Result.PLAYER_WIN, Move.ROCK, Move.SCISSORS),
                Arguments.of(Result.PC_WIN, Move.PAPER, Move.SCISSORS),
                Arguments.of(Result.DRAW, Move.SCISSORS, Move.SCISSORS)
        );
    }
}