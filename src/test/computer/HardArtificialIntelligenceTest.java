package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.BoardMock;
import tictactoe.Player;

public class HardArtificialIntelligenceTest {

  private Player player;

  @BeforeEach
  void setUp() {
    ArtificialIntelligence ai = new HardArtificialIntelligence();
    player = new ComputerPlayer("computer", ai);
  }

  @Test
  void choosesWinningPositionWhenOnlyOneSpaceAvailable() {
    BoardMock board = BoardMock.configureBoard(new int[]{-1, 1, 0, 0, 1, 1, 0, 0, 1}, 0);
    player.playTurn(board);

    assertEquals(0, board.tokenPlacedInPosition);
    assertEquals(0, board.getPositions()[0]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenThreeSpacesAvailable() {
    BoardMock board = BoardMock.configureBoard(new int[]{0, -1, 1, 0, -1, -1, 1, 1, 0}, 0);
    player.playTurn(board);

    assertEquals(4, board.tokenPlacedInPosition);
    assertEquals(0, board.getPositions()[4]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenFiveSpacesAvailable() {
    BoardMock board = BoardMock.configureBoard(new int[]{0, -1, 1, 0, -1, 1, -1, -1, -1}, 0);
    player.playTurn(board);

    assertEquals(6, board.tokenPlacedInPosition);
    assertEquals(0, board.getPositions()[6]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void blocksOtherPlayerFromWinning() {
    BoardMock board = BoardMock.configureBoard(new int[]{1, 0, 0, -1, -1, -1, 1, -1, -1}, 0);
    player.playTurn(board);

    assertEquals(3, board.tokenPlacedInPosition);
    assertEquals(0, board.getPositions()[3]);
    assertFalse(board.gameIsTied());
  }
}