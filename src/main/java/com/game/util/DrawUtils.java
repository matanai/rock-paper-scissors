package com.game.util;

import com.game.config.GameConfig;
import com.game.constant.Message;
import com.game.exception.GameException;
import lombok.experimental.UtilityClass;

/**
 * Provides frequently used methods for building basic application GUI
 * @version 1.0.1
 */
@UtilityClass
public class DrawUtils {

    /**
     * A bit of game atmosphere :) Display ready-steady-go counter before each new game round
     * @throws GameException should {@link Thread#sleep(long)} call go wrong
     */
    public void drawCounter() throws GameException {
        try {
            for (String msg : Message.MSG_DELAY_COUNTER.split(" ")) {
                System.out.print(msg + "\n");
                Thread.sleep(GameConfig.COUNTDOWN_DELAY);
            }
        } catch (InterruptedException e) {
            System.err.printf(Message.MSG_ERR_CONCURRENT, e);
            throw new GameException(e.getMessage());
        }
    }

    /**
     * Build a String line with length, equal to {@link GameConfig#SCREEN_WIDTH}, and filled with
     * a predefined placeholder
     * @return String line with placeholder
     */
    public String getLine() {
        return GameConfig.LINE_PLACEHOLDER.repeat(GameConfig.SCREEN_WIDTH);
    }

    /**
     * Build String line with length, equal to {@link GameConfig#SCREEN_WIDTH}, with a custom message
     * in the center and optional predefined placeholder as a filler. If message length exceeds
     * predefined length return empty String
     *
     * @param message to align in the center
     * @param withPlaceholder if true, use placeholder; otherwise fill in whitespaces
     * @return String line with message aligned in the center
     */
    public String getTextCenter(String message, boolean withPlaceholder) {
        message = " " + message.trim() + " ";

        if (message.length() > GameConfig.SCREEN_WIDTH) {
            return "";
        }

        var sb = new StringBuilder();
        int center = (GameConfig.SCREEN_WIDTH - message.length()) / 2;

        for (int i = 0; i < GameConfig.SCREEN_WIDTH; i++) {
            if (i == center) {
                sb.append(message);
                i += message.length();
            }

            sb.append(withPlaceholder ? GameConfig.LINE_PLACEHOLDER : " ");
        }

        return sb.toString();
    }
}
