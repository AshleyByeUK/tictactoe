package tictactoe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import console.ConsoleUIMock;
import human.HumanPlayerSpy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictacttoe.Game;

public class GameTest {

  private ConsoleUIMock mockConsoleUI;
  private HumanPlayerSpy player1Spy;
  private HumanPlayerSpy player2Spy;
  private Game game;

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    player1Spy = new HumanPlayerSpy("X", "player1");
    player2Spy = new HumanPlayerSpy("O", "player2");
    game = new Game(player1Spy, player2Spy, mockConsoleUI);
  }

  @Test
  void playerOneMakesTheFirstMove() {
    game.nextTurn();

    Assertions.assertTrue(player1Spy.playedMove);
    Assertions.assertEquals("player1", mockConsoleUI.callersName);
    Assertions.assertFalse(player2Spy.playedMove);
  }

  @Test
  void playerTwoMakesTheSecondMove() {
    game.nextTurn();
    game.nextTurn();

    Assertions.assertTrue(player1Spy.playedMove);
    Assertions.assertTrue(player2Spy.playedMove);
    Assertions.assertEquals("player2", mockConsoleUI.callersName);
  }

  @Test
  void playerTwoCanGoFirst() {
    game.setFirstPlayer(1);
    game.nextTurn();

    Assertions.assertTrue(player2Spy.playedMove);
    Assertions.assertEquals("player2", mockConsoleUI.callersName);
    Assertions.assertFalse(player1Spy.playedMove);
  }

  @Test
  void afterNineMovesTheGameIsTied() {
    for (int i = 0; i < 9; i++)
      game.nextTurn();

    assertTrue(game.gameIsTied());
  }
}
