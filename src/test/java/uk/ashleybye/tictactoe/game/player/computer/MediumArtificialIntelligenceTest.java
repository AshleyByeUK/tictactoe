package uk.ashleybye.tictactoe.game.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.impl.BoardImplMock;

class MediumArtificialIntelligenceTest {

  private Player player;

  @BeforeEach
  void setUp() {
    Random randomStub = new RandomStub();
    ArtificialIntelligence ai = new MediumArtificialIntelligence(randomStub);
    player = new ComputerPlayer("computer", ai);
  }

  @Test
  void choosesCentrePositionIfAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard();
    GameState gameState = new GameState(board, 0, 1);
    player.playTurn(gameState);

    assertEquals(4, board.symbolPlacedInPosition);
  }

  @Test
  void choosesWinningPositionIfAvailable() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1});
    GameState gameState = new GameState(board, 1, 0);
    player.playTurn(gameState);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0});
    GameState gameState = new GameState(board, 1, 0);
    player.playTurn(gameState);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    BoardImplMock board = BoardImplMock.configureBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1});
    GameState gameState = new GameState(board, 1, 0);
    player.playTurn(gameState);

    assertEquals(0, board.symbolPlacedInPosition);
  }
}
