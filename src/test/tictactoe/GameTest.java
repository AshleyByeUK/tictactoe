package tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import console.ConsoleUIMock;
import human.HumanPlayerSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictacttoe.Game;

public class GameTest {

  private ConsoleUIMock mockConsoleUI;
  private HumanPlayerSpy player1Spy;
  private HumanPlayerSpy player2Spy;
  private Game game;

  private void assertGameIsNotOver(int[] board) {
    game.board = board;
    assertFalse(game.gameIsOver());
  }

  private void assertGameIsOver(int[] board) {
    game.board = board;
    assertTrue(game.gameIsOver());
  }

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

  @Test
  void positionIsAvailable() {
    assertTrue(game.positionIsAvailable(1));
  }

  @Test
  void positionIsNotAvailable() {
    game.board[1] = 1;

    assertFalse(game.positionIsAvailable(1));
  }

  @Test
  void afterNineMovesTheGameIsTied() {
    for (int i = 0; i < 9; i++)
      game.nextTurn();

    assertTrue(game.gameIsTied());
  }

  @Test
  void gameNotOver() {
    assertGameIsNotOver(new int[]{1, 0, 1, -1, -1, -1, -1, -1, -1});
    assertGameIsNotOver(new int[]{-1, -1, -1, 1, 0, 1, -1, -1, -1});
    assertGameIsNotOver(new int[]{-1, -1, -1, -1, -1, -1, 0, 1, 1});
    assertGameIsNotOver(new int[]{1, -1, -1, 0, -1, -1, 1, -1, -1});
    assertGameIsNotOver(new int[]{-1, 1, -1, -1, 0, -1, -1, 1, -1});
    assertGameIsNotOver(new int[]{-1, -1, 1, -1, -1, 0, -1, -1, 1});
    assertGameIsNotOver(new int[]{1, -1, -1, -1, 0, -1, -1, -1, 1});
    assertGameIsNotOver(new int[]{-1, -1, 0, -1, 1, -1, 1, -1, -1});
  }

  @Test
  void gameOver() {
    assertGameIsOver(new int[]{1, 1, 1, -1, -1, -1, -1, -1, -1});
    assertGameIsOver(new int[]{-1, -1, -1, 1, 1, 1, -1, -1, -1});
    assertGameIsOver(new int[]{-1, -1, -1, -1, -1, -1, 1, 1, 1});
    assertGameIsOver(new int[]{1, -1, -1, 1, -1, -1, 1, -1, -1});
    assertGameIsOver(new int[]{-1, 1, -1, -1, 1, -1, -1, 1, -1});
    assertGameIsOver(new int[]{-1, -1, 1, -1, -1, 1, -1, -1, 1});
    assertGameIsOver(new int[]{1, -1, -1, -1, 1, -1, -1, -1, 1});
    assertGameIsOver(new int[]{-1, -1, 1, -1, 1, -1, 1, -1, -1});
  }
}
