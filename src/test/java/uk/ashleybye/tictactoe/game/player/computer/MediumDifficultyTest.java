package uk.ashleybye.tictactoe.game.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.impl.BoardImplMock;

class MediumDifficultyTest {

  private Player player;
  private GameState gameState;

  @BeforeEach
  void setUp() {
    Random randomStub = new RandomStub();
    player = new ComputerPlayer("computer", "X", new MediumDifficulty(randomStub));
    Player[] players = new Player[]{player, player};
    gameState = new GameState();
    gameState.setCurrentPlayer(0);
    gameState.setNextPlayer(1);
    gameState.setPlayers(players);
  }

  @Test
  void choosesCentrePositionIfAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard();
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(4, board.symbolPlacedInPosition);
  }

  @Test
  void choosesWinningPositionIfAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1});
    gameState.setBoard(board);
    player.playTurn(gameState);

    assertEquals(0, board.symbolPlacedInPosition);
  }
}
