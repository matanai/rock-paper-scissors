package com.game.engine;

import com.game.constant.Move;
import com.game.constant.Result;

/**
 * Apply rules to the current game and calculate {@link Result}
 * @version 1.0.1
 */
public class ResultProcessor {

    /**
     * If result is not a draw and player is not a winner, then PC won
     * @param playerMove move made by player
     * @param pcMove move made by PC
     * @return game result
     */
    public Result calculateGameResult(final Move playerMove, final Move pcMove) {
        if (playerMove == pcMove) {
            return Result.DRAW;
        } else if (hasPlayerWon(playerMove, pcMove)) {
            return Result.PLAYER_WIN;
        } else {
            return Result.PC_WIN;
        }
    }

    /**
     * Calculate if player won (ROCK beats SCISSORS, SCISSORS beats PAPER, PAPER beats ROCK)
     * @param playerMove move made by player
     * @param pcMove move made by PC
     * @return true if PlayerOne wins
     */
    private boolean hasPlayerWon(Move playerMove, Move pcMove) {
        if (playerMove == Move.ROCK) {
            return pcMove == Move.SCISSORS;
        } else if (playerMove == Move.SCISSORS) {
            return pcMove == Move.PAPER;
        } else { // PAPER
            return pcMove == Move.ROCK;
        }
    }
}

