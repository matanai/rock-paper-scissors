package com.game.constant;

/**
 * All possible game outcomes
 * @version 1.0.1
 */
public enum Result {

    PLAYER_WIN("You win"),
    PC_WIN("PC win"),
    DRAW("Draw");

    private final String value;

    Result(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
