package com.game.engine.mode;

import com.game.constant.Move;

import java.util.Random;

/**
 * Implementation of {@link ModeGenerator}. Provides randomly generated (fair) PC {@link Move}
 * @version 1.0.1
 */
public class FairModeGenerator implements ModeGenerator {

    private static final int RANGE = Move.values().length;

    @Override
    public Move generateMove() {
        var num = new Random().nextInt(RANGE);
        return Move.values()[num];
    }
}
