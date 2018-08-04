package uk.ashleybye.tictactoe.game.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTest {

  private BoardImpl board;

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
    board = new BoardImpl();
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
    board.placeSymbolAtPosition(0, 0);
    assertEquals(0, board.getPositions()[0]);
  }

  @Test
  void symbolIsPlacedForPlayerTwo() {
    board.placeSymbolAtPosition(0, 1);
    assertEquals(1, board.getPositions()[0]);
  }

  @Test
  void afterNineMovesTheGameIsTied() {
    board.placeSymbolAtPosition(0, 1);
    board.placeSymbolAtPosition(1, 0);
    board.placeSymbolAtPosition(2, 1);
    board.placeSymbolAtPosition(3, 0);
    board.placeSymbolAtPosition(4, 1);
    board.placeSymbolAtPosition(5, 0);
    board.placeSymbolAtPosition(6, 1);
    board.placeSymbolAtPosition(7, 0);
    board.placeSymbolAtPosition(8, 1);

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
