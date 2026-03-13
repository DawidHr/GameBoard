package com.dawiid21.GameBoard;

import com.dawiid21.GameBoard.model.MainBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameBoardApplication {

	public static void main(String[] args) {
		MainBoard mainBoard = new MainBoard();
		mainBoard.start();
		//SpringApplication.run(GameBoardApplication.class, args);
	}

}
