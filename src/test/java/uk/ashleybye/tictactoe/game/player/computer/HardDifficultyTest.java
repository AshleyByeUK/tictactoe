package uk.ashleybye.tictactoe.game.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.impl.BoardImplMock;

class HardDifficultyTest {

  private Player player;
  private Player[] players;
  private GameState gameState;

  @BeforeEach
  void setUp() {
    player = new ComputerPlayer("computer", "X", new HardDifficulty());
    players = new Player[]{player, player};
    gameState = new GameState();
    gameState.setCurrentPlayer(0);
    gameState.setNextPlayer(1);
    gameState.setPlayers(players);
  }

  @Test
  void choosesWinningPositionWhenOnlyOneSpaceAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{-1, 1, 0, 0, 1, 1, 0, 0, 1});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(0, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[0]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenThreeSpacesAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{0, -1, 1, 0, -1, -1, 1, 1, 0});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(4, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[4]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void choosesWinningPositionWhenFiveSpacesAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{0, -1, 1, 0, -1, 1, -1, -1, -1});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(6, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[6]);
    assertTrue(board.gameIsWon());
  }

  @Test
  void blocksOtherPlayerFromWinning() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{1, 0, 0, -1, -1, -1, 1, -1, -1});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(3, board.symbolPlacedInPosition);
    assertEquals(0, board.getPositions()[3]);
    assertFalse(board.gameIsTied());
  }
}
