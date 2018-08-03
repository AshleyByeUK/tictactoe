package uk.ashleybye.tictactoe.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.GameState;
import uk.ashleybye.tictactoe.Player;
import uk.ashleybye.tictactoe.game.TicTacToeBoardMock;


public class HardArtificialIntelligenceTest {

  private Player player;

  @BeforeEach
  void setUp() {
    ArtificialIntelligence ai = new HardArtificialIntelligence();
    player = new ComputerPlayer("computer", ai);
  }

  @Test
  void choosesWinningPositionWhenOnlyOneSpaceAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{-1, 1, 0, 0, 1, 1, 0, 0, 1});
    GameState gameState = new GameState(board, 0, 1);
    player.playTurn(gameState);

    assertEquals(0, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[0]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenThreeSpacesAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{0, -1, 1, 0, -1, -1, 1, 1, 0});
    GameState gameState = new GameState(board, 0, 1);
    player.playTurn(gameState);

    assertEquals(4, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[4]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenFiveSpacesAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{0, -1, 1, 0, -1, 1, -1, -1, -1});
    GameState gameState = new GameState(board, 0, 1);
    player.playTurn(gameState);

    assertEquals(6, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[6]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void blocksOtherPlayerFromWinning() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{1, 0, 0, -1, -1, -1, 1, -1, -1});
    GameState gameState = new GameState(board, 0, 1);
    player.playTurn(gameState);

    assertEquals(3, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[3]);
    assertFalse(board.gameIsTied());
  }
}
