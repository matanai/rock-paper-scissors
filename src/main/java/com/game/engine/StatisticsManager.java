package com.game.engine;

import com.game.config.GameConfig;
import com.game.constant.Message;
import com.game.constant.Move;
import com.game.constant.Result;
import com.game.util.DrawUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * StatisticsManager collects game results per each round and overall statistics of all games
 * @version 1.0.1
 */
public class StatisticsManager {

    private final List<Game> gameList = new ArrayList<>(GameConfig.GAME_ROUNDS);

    /**
     * Collect game result per each round (each moves and winner), ad to the list, format
     * and return as String
     * @param playerMove move made by player
     * @param pcMove move made by PC
     * @param result winner
     * @return formatted game result
     */
    @SuppressWarnings("all")
    public String buildGameResult(Move playerMove, Move pcMove, Result result) {
        var game = new Game(playerMove, pcMove, result);
        gameList.add(game);

        return new StringBuilder()
                .append(DrawUtils.getLine())
                .append("\n")
                .append(DrawUtils.getTextCenter(String.format(Message.MSG_RESULT_MOVES,
                        game.getPlayerMove(), game.getPcMove()), false))
                .append("\n")
                .append(DrawUtils.getTextCenter(game.getResult().toString(), false))
                .append("\n")
                .append(DrawUtils.getLine())
                .append("\n")
                .toString();
    }

    /**
     * Collect overall game statistics, format, and return as String
     * @return formatted game statistics
     */
    @SuppressWarnings("all")
    public String buildStatistics() {
        return new StringBuilder()
                .append("\n")
                .append(DrawUtils.getTextCenter(Message.MSG_RESULT_LOG, true))
                .append("\n")
                .append(Message.MSG_RESULT_DATE_TIME)
                .append(LocalDateTime.now().format(GameConfig.DATE_TIME_FORMATTER))
                .append("\n")
                .append(Message.MSG_RESULT_GAMES_PLAYED)
                .append(gameList.size())
                .append("\n")
                .append(buildScore())
                .toString();
    }

    /**
     * Calculate number of player wins, PC wins and draws, format result and return as string
     * @return formatted score of each player
     */
    private String buildScore() {
        return gameList.stream()
                .collect(Collectors.groupingBy(Game::getResult, Collectors.counting()))
                .entrySet().stream()
                .map(e -> String.format(Message.MSG_RESULT_SCORE_MAP, e.getKey(), e.getValue()))
                .collect(Collectors.joining());
    }

    /**
     * Class to represent each individual game
     * @version 1.0.1
     */
    @AllArgsConstructor
    @Getter
    private static class Game {

        private final Move playerMove, pcMove;
        private final Result result;
    }

}
