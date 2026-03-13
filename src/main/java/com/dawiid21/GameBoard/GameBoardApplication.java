package com.dawiid21.GameBoard;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameBoardApplication {

	public static void main(String[] args) {
		ConsoleUI consoleUI = new ConsoleUI();
		consoleUI.start();
	}

}
