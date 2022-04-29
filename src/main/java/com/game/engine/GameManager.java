package com.game.engine;

import com.game.config.GameConfig;
import com.game.constant.Message;
import com.game.constant.Move;
import com.game.engine.mode.FairModeGenerator;
import com.game.engine.mode.ModeGenerator;
import com.game.engine.mode.UnfairModeGenerator;
import com.game.exception.GameException;
import com.game.util.DrawUtils;
import com.game.util.FileUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;

/**
 * Main class which creates and runs new games. Each game starts with collecting user data to configure game
 * instance (game mode, save to file option), following a simple game loop and results being displayed on
 * screen after each game round. Upon termination of the loop, statistical information is displayed on screen,
 * containing total games played and outcomes per each game. User can also save this data to a file.
 * @version 1.0.1
 */
public class GameManager {

    private final ResultProcessor resultProcessor = new ResultProcessor();
    private final StatisticsManager statisticsManager = new StatisticsManager();
    private ModeGenerator modeGenerator = new FairModeGenerator();

    private String fileName = GameConfig.DEFAULT_FILE_NAME;
    private boolean saveToFile = false;
    private Move playerMove, pcMove;

    /**
     * Initiates game loop and provides basic interaction with the user
     */
    public void startNewGame() {
        out.println(DrawUtils.getTextCenter(Message.MSG_GAME_LOGO, true));

        try (var input = new BufferedReader(new InputStreamReader(in))) {

            displayChooseMode(input);
            displayChooseFile(input);

            // Game loop starts here
            for (int i = 1; i <= GameConfig.GAME_ROUNDS; i++) {
                out.println(DrawUtils.getTextCenter(String.format(Message.MSG_ROUND_LOGO, i), true));
                DrawUtils.drawCounter();

                displayChooseMove(input);
                displayGameResult();

                out.print(Message.MSG_CHOOSE_CONTINUE);
                if (input.readLine().equals("q")) {
                    break;
                }
            }

            displayStatistics();

        } catch (GameException e) {
            err.println(e.getMessage());
        } catch (IOException e) {
            err.printf(Message.MSG_ERR_READ_IN, e);
        }
    }

    /**
     * Displays game result, provided by {@link ResultProcessor} and formatted by {@link StatisticsManager}
     */
    private void displayGameResult() {
        var result = resultProcessor.calculateGameResult(playerMove, pcMove);
        var resultToDisplay = statisticsManager.buildGameResult(playerMove, pcMove, result);
        out.print(resultToDisplay);
    }

    /**
     * Displays statistics, provided by {@link StatisticsManager}. Optionally, save statistics in a user
     * defined file via {@link FileUtils}
     */
    private void displayStatistics() {
        var statisticsToDisplay = statisticsManager.buildStatistics();
        out.print(statisticsToDisplay);

        if (saveToFile) {
            FileUtils.saveToFile(statisticsToDisplay, fileName);
        }
    }

    /**
     * Asks user to choose next move while generating PC move using the provided {@link ModeGenerator}
     * @throws IOException should the {@link BufferedReader#readLine()} fail
     */
    private void displayChooseMove(final BufferedReader input) throws IOException {
        while (true) {
            out.print(Message.MSG_CHOOSE_MOVE);
            var userInput = input.readLine();
            if (userInput.equals("r") || userInput.equals("p") || userInput.equals("s")) {
                playerMove = parsePlayerMove(userInput);
                pcMove = modeGenerator.generateMove();
                break;
            } else {
                out.print(Message.MSG_WRONG_INPUT);
            }
        }
    }

    /**
     * Asks user to choose whether statistics must be stored in a file. If yes, asks to provide
     * a file name and sets corresponding boolean flag to true
     * @throws IOException should the {@link BufferedReader#readLine()} fail
     * @see FileUtils
     */
    private void displayChooseFile(final BufferedReader input) throws IOException {
        while (true) {
            out.print(Message.MSG_CHOOSE_SAVE);
            var userInput = input.readLine();
            if (userInput.equals("y")) {
                out.printf(Message.MSG_CHOOSE_FILE);
                fileName = input.readLine();
                saveToFile = true;
                break;
            } else if (userInput.equals("n")) {
                break;
            } else {
                out.print(Message.MSG_WRONG_INPUT);
            }
        }
    }

    /**
     * Asks user to choose game mode and instantiates appropriate mode generator
     * @throws IOException should the {@link BufferedReader#readLine()} fail
     * @see ModeGenerator
     */
    private void displayChooseMode(final BufferedReader input) throws IOException {
        while (true) {
            out.print(Message.MSG_CHOOSE_MODE);
            var userInput = input.readLine();
            if (userInput.equals("f")) {
                break;
            } else if (userInput.equals("u")) {
                modeGenerator = new UnfairModeGenerator();
                break;
            } else {
                out.print(Message.MSG_WRONG_INPUT);
            }
        }
    }

    /**
     * Returns user input mapped to a particular {@link Move}
     */
    private Move parsePlayerMove(final String input) {
        if (input.equals("r")) {
            return Move.ROCK;
        } else if (input.equals("p")) {
            return Move.PAPER;
        } else {
            return Move.SCISSORS;
        }
    }
}
