package com.dawiid21.GameBoard;

import com.dawiid21.GameBoard.model.Game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameService {
    private final List<Game> games = new ArrayList<>();

    public void addGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        boolean gameAlreadyExists = games.stream().anyMatch(g -> g.equals(game) && g.isGameInProgress());
        if (gameAlreadyExists) {
            System.out.println("Game between " + homeTeam + " and " + awayTeam + " is already in progress");
            return;
        }
        games.add(game);
    }

    public void finishGame(long gameId) {
        Game game = games.stream().filter(g -> g.getGameId() == gameId).findFirst().orElse(null);
        if (game != null) {
            game.finishGame();
            System.out.println("Game with id: " + gameId + " has been finished");
        } else {
            System.out.println("Failure to recognize the game");
        }
    }

    public void updateScore(long gameId, int teamId, long addScore) {
        Game game = games.stream().filter(g -> g.getGameId() == gameId).findFirst().orElse(null);
        if (game != null) {
            if (teamId == 1) {
                game.addHomeTeamScore(addScore);
            } else if (teamId == 2) {
                game.addAwayTeamScore(addScore);
            } else {
                System.out.println("Failure to recognize the team");
            }
        } else {
            System.out.println("Failure to recognize the game");
        }
    }

    public boolean isGameExistAndIsInProgress(long gameId) {
        return games.stream().anyMatch(g -> g.getGameId() == gameId && g.isGameInProgress());
    }

    public List<Game> getGamesInProgress() {
        return games.stream().filter(Game::isGameInProgress).toList();
    }

    public List<Game> getGames() {
        return games;
    }

    public LinkedList<Game> getSummaryGames() {
        LinkedList<Game> gamesFinished = games.stream()
                .filter(g -> !g.isGameInProgress())
                .collect(Collectors.toCollection(LinkedList::new));
        gamesFinished.sort(new GameSummaryComparator());
        return gamesFinished;
    }

}
