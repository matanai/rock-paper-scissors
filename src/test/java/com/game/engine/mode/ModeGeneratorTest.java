package com.game.engine.mode;

import com.game.constant.Move;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ModeGeneratorTest {

    @RepeatedTest(10)
    void testFairModeGenerator() {
        var modeGenerator = new FairModeGenerator();

        var itemSet = Arrays.stream(Move.values())
                .collect(Collectors.toSet());

        var item = modeGenerator.generateMove();
        assertTrue(itemSet.contains(item));
    }

    @RepeatedTest(10)
    void testUnfairModeGenerator() {
        var modeGenerator = new UnfairModeGenerator();
        assertEquals(Move.ROCK, modeGenerator.generateMove());
    }

}