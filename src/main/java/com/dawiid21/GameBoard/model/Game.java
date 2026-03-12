package com.dawiid21.GameBoard.model;


import java.util.Objects;

public class Game {
    private long gameId;
    private String homeTeam;
    private String awayTeam;
    private long homeTeamScore;
    private long awayTeamScore;
    private boolean gameInProgress;

    private static long id = 1;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        homeTeamScore = 0;
        awayTeamScore = 0;
        gameInProgress = true;
        gameId = id;
        id++;
    }

    public long getGameId() {
        return gameId;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public long getHomeTeamScore() {
        return homeTeamScore;
    }

    public long getAwayTeamScore() {
        return awayTeamScore;
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void  addHomeTeamScore(long addScore) {
        homeTeamScore += addScore;
    }

    public void  addAwayTeamScore(long addScore) {
        awayTeamScore += addScore;
    }

    public void  finishGame() {
        gameInProgress = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (gameId == game.gameId) return true;
        return homeTeam.equals(game.homeTeam) && awayTeam.equals(game.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, homeTeam, awayTeam);
    }
}
