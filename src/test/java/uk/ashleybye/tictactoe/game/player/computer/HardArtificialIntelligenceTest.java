package uk.ashleybye.tictactoe.game.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.impl.BoardImplMock;


public class HardArtificialIntelligenceTest {

  private Player player;
  private Player[] players;

  @BeforeEach
  void setUp() {
    ArtificialIntelligence ai = new HardArtificialIntelligence();
    player = new ComputerPlayer("computer", "X", ai);
    players = new Player[]{player, player};
  }

  @Test
  void choosesWinningPositionWhenOnlyOneSpaceAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{-1, 1, 0, 0, 1, 1, 0, 0, 1});
    GameState gameState = new GameState(board, 0, 1, players);
    player.playTurn(gameState);

    assertEquals(0, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[0]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenThreeSpacesAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{0, -1, 1, 0, -1, -1, 1, 1, 0});
    GameState gameState = new GameState(board, 0, 1, players);
    player.playTurn(gameState);

    assertEquals(4, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[4]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenFiveSpacesAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{0, -1, 1, 0, -1, 1, -1, -1, -1});
    GameState gameState = new GameState(board, 0, 1, players);
    player.playTurn(gameState);

    assertEquals(6, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[6]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void blocksOtherPlayerFromWinning() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{1, 0, 0, -1, -1, -1, 1, -1, -1});
    GameState gameState = new GameState(board, 0, 1, players);
    player.playTurn(gameState);

    assertEquals(3, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[3]);
    assertFalse(board.gameIsTied());
  }
}
