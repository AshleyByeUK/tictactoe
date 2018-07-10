package tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {

  private Board board;

  private void assertGameIsNotOver(int[] positions) {
    board.positions = positions;
    assertFalse(board.gameIsOver());
  }

  private void assertGameIsOver(int[] positions) {
    board.positions = positions;
    assertTrue(board.gameIsOver());
  }

  @BeforeEach
  void setUp() {
    board = new Board();
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
  void tokenIsPlacedForPlayerOne() {
    board.setCurrentPlayer(0);
    board.placeToken(0);
    assertEquals(0, board.getPositions()[0]);
  }

  @Test
  void tokenIsPlacedForPlayerTwo() {
    board.setCurrentPlayer(1);
    board.placeToken(0);
    assertEquals(1, board.getPositions()[0]);
  }

  @Test
  void afterNineMovesTheGameIsTied() {
    board.movesMade = 9;

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