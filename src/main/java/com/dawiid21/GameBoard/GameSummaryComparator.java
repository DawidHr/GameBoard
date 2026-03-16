package com.dawiid21.GameBoard;

import com.dawiid21.GameBoard.model.Game;

import java.util.Comparator;

public class GameSummaryComparator implements Comparator<Game> {
    @Override
    public int compare(Game game1, Game game2) {
        long totalScore1 = game1.getHomeTeamScore() + game1.getAwayTeamScore();
        long totalScore2 = game2.getHomeTeamScore() + game2.getAwayTeamScore();
        if(totalScore1 != totalScore2) {
            return Long.compare(totalScore2, totalScore1);
        } else {
            return Long.compare(game1.getGameId(), game2.getGameId());
        }
    }
}
