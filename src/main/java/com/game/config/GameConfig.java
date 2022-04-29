package com.game.config;

import lombok.experimental.UtilityClass;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Collection of configurable parameters of the application
 * @version 1.0.1
 */
@UtilityClass
public class GameConfig {

    public final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    public final String DEFAULT_FILE_NAME = "score";
    public final String DEFAULT_FILE_EXTENSION = ".txt";
    public final String LINE_PLACEHOLDER = "~";
    public final long COUNTDOWN_DELAY = 400L;
    public final int SCREEN_WIDTH = 80;
    public final int GAME_ROUNDS = 10;
}
