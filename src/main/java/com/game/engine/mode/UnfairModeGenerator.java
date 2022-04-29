package com.game.engine.mode;

import com.game.constant.Move;

/**
 * Implementation of {@link ModeGenerator}. Provides predefined (unfair) PC {@link Move}
 * @version 1.0.1
 */
public class UnfairModeGenerator implements ModeGenerator {

    @Override
    public Move generateMove() {
        return Move.ROCK;
    }
}
