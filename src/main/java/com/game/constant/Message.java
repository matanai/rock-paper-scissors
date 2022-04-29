package com.game.constant;

import lombok.experimental.UtilityClass;

/**
 * Collection of constants for each String message used in the application
 * @version 1.0.1
 */
@UtilityClass
public final class Message {

    public final String MSG_GAME_LOGO = "ROCK-PAPER-SCISSORS";
    public final String MSG_ROUND_LOGO = "ROUND %d";
    public final String MSG_RESULT_LOG = "GAME RESULT";
    public final String MSG_CHOOSE_MODE = "Choose: 'f' - Fair (PC makes random moves), 'u' - Unfair (PC is always ROCK): ";
    public final String MSG_CHOOSE_SAVE = "Choose: 'y' - Save to file, 'n' - Don't save to file: ";
    public final String MSG_CHOOSE_FILE = "Choose: file name (extension must be '.txt'): ";
    public final String MSG_CHOOSE_MOVE = "Choose: 'r' - Rock, 'p' - Paper, 's' - Scissors: ";
    public final String MSG_CHOOSE_CONTINUE = "Choose: any key - continue, 'q' - show results and exit: ";
    public final String MSG_DELAY_COUNTER = "3... 2... 1... Go!";
    public final String MSG_WRONG_INPUT = "Wrong input: ";
    public final String MSG_RESULT_MOVES = "'%s' (You) vs '%s' (PC)";
    public final String MSG_RESULT_DATE_TIME = "Date, time: ";
    public final String MSG_RESULT_GAMES_PLAYED = "Games played: ";
    public final String MSG_RESULT_SCORE_MAP = "%s: %s%n";
    public final String MSG_ERR_FILE_OUT = "Error writing to file: %s";
    public final String MSG_ERR_READ_IN = "Error reading from input: %s";
    public final String MSG_ERR_CONCURRENT = "Error in delayed countdown: %s";

}
