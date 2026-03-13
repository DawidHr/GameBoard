package com.dawiid21.GameBoard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainBoard {
    Scanner scanner = new Scanner(System.in);
    List<Game> games = new ArrayList<>();

    public void showGameBoard() {
        System.out.println("========================================================================");
        System.out.println("Games in progress");
        List<Game> gameInProgress = games.stream().filter(Game::isGameInProgress).toList();
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
        System.out.println("3. Get a summary of games by total score");
        System.out.println("========================================================================");
    }

    public void start() {
        while(true) {
            showGameBoard();
            showMainActionList();
            System.out.println("Select Action: ");
            int selectedAction = scanner.nextInt();
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
                    System.out.println("Wybrałeś 4");
                    break;
                }
                default: {
                    System.out.println("Failure to recognize the action");
                    break;
                }
            }
        }
    }


    public void addGame() {
        System.out.println("Action Selected: Add Game");
        System.out.println("Please provide home team");
        String homeTeam = scanner.next();
        System.out.println("Home team name: "+ homeTeam);
        System.out.println("Please provide away team");
        String awayTeam = scanner.next();
        System.out.println("Home team name: "+ awayTeam);
        games.add(new Game(homeTeam, awayTeam));
    }

    public void finishGame() {
        System.out.println("Action Selected: Finish Game");
        System.out.println("Please provide id game to finish");
        int gameId = scanner.nextInt();
        boolean gameExists = games.stream().anyMatch(r -> r.isGameInProgress() && r.getGameId() == gameId);
        if (gameExists) {
            Game game = games.stream().filter(r -> r.isGameInProgress() && r.getGameId() == gameId).findFirst().orElse(null);
            if(game != null)
                game.finishGame();

            System.out.println("The game of id" +gameId+ "has ended");
        } else {
            System.out.println("The game with the given id does not exist");
        }
    }

    public void updateScore() {
        System.out.println("Action Selected: Update Score");
        System.out.println("Select the game to update:");
        int gameIdToUpdate = scanner.nextInt();
        boolean gameExists = games.stream().anyMatch(r -> r.isGameInProgress() && r.getGameId() == gameIdToUpdate);
        if (gameExists) {
            System.out.println("Please select team to update:");
            System.out.println("1. HomeTeam");
            System.out.println("2. AwayTeam");
            int teamToUpdate = scanner.nextInt();
            if(teamToUpdate > 0 && teamToUpdate <= 2) {
                Game game = games.stream().filter(r -> r.isGameInProgress() && r.getGameId() == gameIdToUpdate).findFirst().orElse(null);
                if (game != null) {
                   if(teamToUpdate == 1) {
                       game.addHomeTeamScore(1);
                   } else {
                       game.addAwayTeamScore(1);
                   }
                }
            } else {
                System.out.println("The team with the given id does not exist");
            }
        } else {
            System.out.println("The game with the given id does not exist");
        }
    }
}
