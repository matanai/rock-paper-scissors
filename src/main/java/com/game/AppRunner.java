package com.game;

import com.game.engine.GameManager;

/**
 * Start application
 * @version 1.0.1
 */
public class AppRunner {

    public static void main(String[] args) {
        new GameManager().startNewGame();
    }
}
