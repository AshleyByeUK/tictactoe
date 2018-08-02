package uk.ashleybye.tictactoe.tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TicTacToeBoardTest {

  private TicTacToeBoard board;

  private void assertGameIsNotOver(int[] positions) {
    board.positions = positions;
    assertFalse(board.gameIsWon());
  }

  private void assertGameIsOver(int[] positions) {
    board.positions = positions;
    assertTrue(board.gameIsWon());
  }

  @BeforeEach
  void setUp() {
    board = new TicTacToeBoard();
  }

  @Test
  void positionIsAvailable() {
    assertTrue(board.positionIsAvailable(1));
  }

  @Test
  void positionIsNotAvailable() {
    board.positions[1] = 1;

    assertFalse(board.positionIsAvailable(1));
  }

  @Test
  void symbolIsPlacedForPlayerOne() {
    board.setCurrentPlayer(0);
    board.placeSymbolAtPosition(0);
    assertEquals(0, board.getPositions()[0]);
  }

  @Test
  void symbolIsPlacedForPlayerTwo() {
    board.setCurrentPlayer(1);
    board.placeSymbolAtPosition(0);
    assertEquals(1, board.getPositions()[0]);
  }

  @Test
  void afterNineMovesTheGameIsTied() {
    board.setCurrentPlayer(1);
    board.placeSymbolAtPosition(0);
    board.placeSymbolAtPosition(1);
    board.placeSymbolAtPosition(2);
    board.placeSymbolAtPosition(3);
    board.placeSymbolAtPosition(4);
    board.placeSymbolAtPosition(5);
    board.placeSymbolAtPosition(6);
    board.placeSymbolAtPosition(7);
    board.placeSymbolAtPosition(8);

    assertTrue(board.gameIsTied());
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
