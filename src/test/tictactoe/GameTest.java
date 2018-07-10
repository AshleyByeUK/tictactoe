package tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import console.ConsoleUIMock;
import human.HumanPlayerSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

  private ConsoleUIMock mockConsoleUI;
  private HumanPlayerSpy player1Spy;
  private HumanPlayerSpy player2Spy;
  private Game game;

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    player1Spy = new HumanPlayerSpy("player1");
    player2Spy = new HumanPlayerSpy("player2");
    game = new Game(player1Spy, player2Spy, mockConsoleUI);
  }

  @Test
  void playerOneMakesTheFirstMove() {
    game.nextTurn();

    assertTrue(player1Spy.playedMove);
    assertEquals("player1", mockConsoleUI.callersName);
    assertFalse(player2Spy.playedMove);
  }

  @Test
  void playerTwoMakesTheSecondMove() {
    game.nextTurn();
    game.nextTurn();

    assertTrue(player1Spy.playedMove);
    assertTrue(player2Spy.playedMove);
    assertEquals("player2", mockConsoleUI.callersName);
  }

  @Test
  void playerTwoCanGoFirst() {
    game.setFirstPlayer(1);
    game.nextTurn();

    assertTrue(player2Spy.playedMove);
    assertEquals("player2", mockConsoleUI.callersName);
    assertFalse(player1Spy.playedMove);
  }
}
