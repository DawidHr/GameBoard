package com.dawiid21.GameBoard.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    public void testCreateGame() {
        Game game1 = new Game("homeTeam", "awayTeam");
        Game game2 = new Game("homeTeam1", "awayTeam1");

        assertEquals("homeTeam", game1.getHomeTeam());
        assertEquals("awayTeam", game1.getAwayTeam());
        assertEquals(0, game1.getHomeTeamScore());
        assertEquals(0, game1.getAwayTeamScore());
        assertTrue(game1.isGameInProgress());

        assertEquals("homeTeam1", game2.getHomeTeam());
        assertEquals("awayTeam1", game2.getAwayTeam());
        assertEquals(0, game2.getHomeTeamScore());
        assertEquals(0, game2.getAwayTeamScore());
        assertTrue(game2.isGameInProgress());
    }

    @Test
    public void scoreTest() {
        Game game1 = new Game("homeTeam", "awayTeam");
        assertEquals(0, game1.getHomeTeamScore());
        assertEquals(0, game1.getAwayTeamScore());
        game1.addHomeTeamScore(1);
        assertEquals(1, game1.getHomeTeamScore());
        assertEquals(0, game1.getAwayTeamScore());
        game1.addHomeTeamScore(1);
        game1.addAwayTeamScore(1);
        assertEquals(2, game1.getHomeTeamScore());
        assertEquals(1, game1.getAwayTeamScore());
    }

    @Test
    public void gameFinishTest() {
        Game game1 = new Game("homeTeam", "awayTeam");
        assertTrue(game1.isGameInProgress());
        game1.finishGame();
        assertFalse(game1.isGameInProgress());
    }

    @Test
    public void gameEqualsTest() {
        Game game1 = new Game("homeTeam", "awayTeam");
        Game game2 = new Game("homeTeam", "awayTeam");
        Game game3 = new Game("homeTeam", "awayTeam1");
        Game game4 = new Game("homeTeam1", "awayTeam");
        assertTrue(game1.equals(game2));
        assertTrue(game2.equals(game1));
        assertFalse(game1.equals(game3));
        assertFalse(game1.equals(game4));
        assertFalse(game2.equals(game3));
        assertFalse(game2.equals(game4));
    }
}