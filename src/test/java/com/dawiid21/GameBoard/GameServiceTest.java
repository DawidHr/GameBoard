package com.dawiid21.GameBoard;

import org.junit.jupiter.api.Test;

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
}