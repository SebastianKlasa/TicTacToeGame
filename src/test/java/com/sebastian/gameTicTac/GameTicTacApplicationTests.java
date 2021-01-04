package com.sebastian.gameTicTac;

import com.sebastian.gameTicTac.Model.Game;
import com.sebastian.gameTicTac.Model.Move;
import com.sebastian.gameTicTac.Model.Player;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GameTicTacApplicationTests {

	Game game;
	Player player1;
	Player player2;

	@BeforeEach
	void prepareGame(){
		game = new Game("ABCDEF");
		player1 = new Player();
		player1.setId(1);
		game.addPlayer(player1);
		player2 = new Player();
		player2.setId(2);
		game.addPlayer(player2);
	}

	@Test
	void emptyBoardTest() {
		assertEquals(null, game.checkWinner());
	}

	@Test
	void HorizontalWinnerTest() {
		Move m1 = new Move(player1, 0, 0);
		Move m2 = new Move(player1, 1, 0);
		Move m3 = new Move(player1, 2, 0);
		Move m4 = new Move(player1, 3, 0);
		Move m5 = new Move(player1, 4, 0);
		game.setMove(m1);
		game.changePlayerRound();
		game.setMove(m2);
		game.changePlayerRound();
		game.setMove(m3);
		game.changePlayerRound();
		game.setMove(m4);
		game.changePlayerRound();
		game.setMove(m5);
		game.changePlayerRound();

		System.out.println("1. HorizontalWinnerTest");
		game.getBoard().printContent();

		assertEquals(1, game.checkWinner().getId());
	}

	@Test
	void VerticalWinnerTest() {
		Move m1 = new Move(player1, 8, 4);
		Move m2 = new Move(player1, 8, 5);
		Move m3 = new Move(player1, 8, 6);
		Move m4 = new Move(player1, 8, 7);
		Move m5 = new Move(player1, 8, 3);
		game.setMove(m1);
		game.changePlayerRound();
		game.setMove(m2);
		game.changePlayerRound();
		game.setMove(m3);
		game.changePlayerRound();
		game.setMove(m4);
		game.changePlayerRound();
		game.setMove(m5);
		game.changePlayerRound();

		System.out.println("2. VerticalWinnerTest");
		game.getBoard().printContent();

		assertEquals(1, game.checkWinner().getId());
	}

	@Test
	void ObliquelyWinnerTest() {
		Move m1 = new Move(player1, 1, 4);
		Move m2 = new Move(player1, 2, 5);
		Move m3 = new Move(player1, 3, 6);
		Move m4 = new Move(player1, 4, 7);
		Move m5 = new Move(player1, 0, 3);
		game.setMove(m1);
		game.changePlayerRound();
		game.setMove(m2);
		game.changePlayerRound();
		game.setMove(m3);
		game.changePlayerRound();
		game.setMove(m4);
		game.changePlayerRound();
		game.setMove(m5);
		game.changePlayerRound();

		System.out.println("3. ObliquelyWinnerTest");
		game.getBoard().printContent();

		assertEquals(1, game.checkWinner().getId());
	}

	@Test
	void ObliquelyWinnerTestWithPause() {
		Move m1 = new Move(player1, 1, 4);
		Move m2 = new Move(player1, 2, 5);
		Move m3 = new Move(player1, 3, 6);
		Move m4 = new Move(player2, 4, 7);
		Move m5 = new Move(player1, 0, 3);
		game.setMove(m1);
		game.changePlayerRound();
		game.setMove(m2);
		game.changePlayerRound();
		game.setMove(m3);
		game.changePlayerRound();
		game.setMove(m4);
		game.changePlayerRound();
		game.setMove(m5);
		game.changePlayerRound();

		System.out.println("4. ObliquelyWinnerTest");
		game.getBoard().printContent();

		assertEquals(null, game.checkWinner());
	}

	//TODO: more test cases

}
