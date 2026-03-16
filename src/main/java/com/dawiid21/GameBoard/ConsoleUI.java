package com.dawiid21.GameBoard;

import com.dawiid21.GameBoard.model.Game;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final Scanner scanner = new Scanner(System.in);
    private final GameService gameService = new GameService();


    public void start() {
        while(true) {
            showGameBoard();
            showMainActionList();
            System.out.println("Select Action: ");
            int selectedAction = scanner.nextInt();
            scanner.nextLine();
            switch (selectedAction) {
                case 1: {
                    addGame();
                    break;
                }
                case 2: {
                    finishGame();
                    break;
                }
                case 3: {
                    updateScore();
                    break;
                }
                case 4: {
                    getSummaryGames();
                    break;
                }
                default: {
                    System.out.println("Failure to recognize the action");
                    break;
                }
            }
        }
    }


    public void showGameBoard() {
        System.out.println("========================================================================");
        System.out.println("Games in progress");
        List<Game> gameInProgress = gameService.getGamesInProgress();
        for(Game game: gameInProgress) {
            System.out.println("id: "+game.getGameId()+". "+game.getHomeTeam()+" - "+game.getAwayTeam()+": "+ game.getHomeTeamScore()+"-"+game.getAwayTeamScore());
        }
        System.out.println("========================================================================");
    }

    public void showMainActionList() {
        System.out.println("========================================================================");
        System.out.println("Action List. Select action");
        System.out.println("1. Start a game");
        System.out.println("2. Finish game");
        System.out.println("3. Update score");
        System.out.println("4. Get a summary of games by total score");
        System.out.println("========================================================================");
    }


    public void addGame() {
        System.out.println("Action Selected: Add Game");
        System.out.println("Please provide home team");
        String homeTeam = scanner.nextLine();
        System.out.println("Home team name: "+ homeTeam);
        System.out.println("Please provide away team");
        String awayTeam = scanner.nextLine();
        System.out.println("Home team name: "+ awayTeam);
        gameService.addGame(homeTeam, awayTeam);
    }

    public void finishGame() {
        System.out.println("Action Selected: Finish Game");
        System.out.println("Please provide id game to finish");
        int gameId = scanner.nextInt();
        gameService.finishGame(gameId);
    }

    public void updateScore() {
        System.out.println("Action Selected: Update Score");
        System.out.println("Select the game to update:");
        int gameIdToUpdate = scanner.nextInt();
        boolean gameExists = gameService.isGameExistAndIsInProgress(gameIdToUpdate);
        if (gameExists) {
            System.out.println("Please select team to update:");
            System.out.println("1. HomeTeam");
            System.out.println("2. AwayTeam");
            int teamToUpdate = scanner.nextInt();
            gameService.updateScore(gameIdToUpdate, teamToUpdate, 1);
        } else {
            System.out.println("The game with the given id does not exist");
        }
    }

    public void getSummaryGames() {
        System.out.println("Action Selected: Get Summary Games");
        LinkedList<Game> gamesInProgress = gameService.getSummaryGames();
        for (Game game : gamesInProgress) {
            System.out.println("id: " + game.getGameId() + ". " + game.getHomeTeam() + " - " + game.getAwayTeam() + ": " + game.getHomeTeamScore() + "-" + game.getAwayTeamScore());
        }
    }
}
