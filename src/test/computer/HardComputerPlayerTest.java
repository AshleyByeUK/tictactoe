package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import tictactoe.BoardMock;
import tictactoe.Player;

public class HardComputerPlayerTest {

  private BoardMock configureBoard(int[] positions, int currentPlayer) {
    BoardMock board = new BoardMock();
    board.setPositions(positions);
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(currentPlayer == 0 ? 1 : 0);
    return board;
  }

  @Test
  void choosesWinningPositionWhenOnlyOneSpaceAvailable() {
    Player player = new HardComputerPlayer("computer");

    BoardMock board = configureBoard(new int[]{-1, 1, 0, 0, 1, 1, 0, 0, 1}, 0);
    player.playTurn(board);

    assertEquals(0, board.positionPlayed);
    assertEquals(0, board.getPositions()[0]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenThreeSpacesAvailable() {
    Player player = new HardComputerPlayer("computer");
    BoardMock board = configureBoard(new int[]{0, -1, 1, 0, -1, -1, 1, 1, 0}, 0);
    player.playTurn(board);

    assertEquals(4, board.positionPlayed);
    assertEquals(0, board.getPositions()[4]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenFiveSpacesAvailable() {
    Player player = new HardComputerPlayer("computer");
    BoardMock board = configureBoard(new int[]{0, -1, 1, 0, -1, 1, -1, -1, -1}, 0);
    player.playTurn(board);

    assertEquals(6, board.positionPlayed);
    assertEquals(0, board.getPositions()[6]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void blocksOtherPlayerFromWinning() {
    Player player = new HardComputerPlayer("computer");
    BoardMock board = configureBoard(new int[]{1, 0, 0, -1, -1, -1, 1, -1, -1}, 0);
    player.playTurn(board);

    assertEquals(3, board.positionPlayed);
    assertEquals(0, board.getPositions()[3]);
    assertFalse(board.gameIsTied());
  }
}
