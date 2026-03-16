package com.dawiid21.GameBoard;

import com.dawiid21.GameBoard.model.Game;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


class GameServiceTest {

    GameService gameService = new GameService();

    @Test
    void addGameTest() {
        assertEquals(0, gameService.getGames().size());
        gameService.addGame("Team A", "Team B");
        assertEquals(1, gameService.getGames().size());
        gameService.addGame("Team A", "Team B");
        assertEquals(1, gameService.getGames().size());
        gameService.addGame("Team C", "Team B");
        assertEquals(2, gameService.getGames().size());
    }

    @Test
    void finishGameTest() {
        gameService = new GameService();
        gameService.addGame("Team A", "Team B");
        long gameId = gameService.getGames().get(0).getGameId();
        assertTrue(gameService.isGameExistAndIsInProgress(gameId));
        gameService.finishGame(gameId);
        assertFalse(gameService.isGameExistAndIsInProgress(gameId));
    }

    @Test
    void finishGameNotExistsTest() {
        gameService = new GameService();
        gameService.addGame("Team A", "Team B");
        long gameId = -1L;
        gameService.finishGame(gameId);
        assertEquals(1, gameService.getGamesInProgress().size());
    }

    @Test
    void updateScoreTest() {
        gameService = new GameService();
        gameService.addGame("Team A", "Team B");
        gameService.updateScore(12, 1,1);
        assertEquals(1, gameService.getGamesInProgress().size());
        Game game = gameService.getGames().get(0);
        assertEquals(0, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
        gameService.updateScore(game.getGameId(), 5,1);
        assertEquals(0, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
        gameService.updateScore(game.getGameId(), 1,1);
        assertEquals(1, game.getHomeTeamScore());
        assertEquals(0, game.getAwayTeamScore());
        gameService.updateScore(game.getGameId(), 2,1);
        assertEquals(1, game.getHomeTeamScore());
        assertEquals(1, game.getAwayTeamScore());
    }

    @Test
    void getSummaryGamesTest() {
        gameService = new GameService();
        gameService.addGame("Team A", "Team B");
        gameService.addGame("Team A 1", "Team B 1");
        gameService.addGame("Team A 2", "Team B 2");
        Game game1 = gameService.getGames().get(0);
        gameService.finishGame(game1.getGameId());
        Game game2 = gameService.getGames().get(1);
        gameService.updateScore(game2.getGameId(), 1, 1);
        gameService.updateScore(game2.getGameId(), 2, 1);
        gameService.updateScore(game2.getGameId(), 1, 1);
        gameService.finishGame(game2.getGameId());
        Game game3 = gameService.getGames().get(2);
        gameService.updateScore(game3.getGameId(), 1, 1);
        gameService.finishGame(game3.getGameId());
        gameService.addGame("Team A 3", "Team B 3");

        LinkedList<Game> results = gameService.getSummaryGames();
        assertEquals(3, results.size());
        assertEquals("Team A 1", results.get(0).getHomeTeam());
        assertEquals("Team A 2", results.get(1).getHomeTeam());

    }
}