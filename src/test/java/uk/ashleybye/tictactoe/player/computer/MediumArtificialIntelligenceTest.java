package uk.ashleybye.tictactoe.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.GameState;
import uk.ashleybye.tictactoe.Player;
import uk.ashleybye.tictactoe.game.TicTacToeBoardMock;

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
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard();
    GameState gameState = new GameState(board, 0, 1);
    player.playTurn(gameState);

    assertEquals(4, board.symbolPlacedInPosition);
  }

  @Test
  void choosesWinningPositionIfAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1});
    GameState gameState = new GameState(board, 1, 0);
    player.playTurn(gameState);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0});
    GameState gameState = new GameState(board, 1, 0);
    player.playTurn(gameState);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1});
    GameState gameState = new GameState(board, 1, 0);
    player.playTurn(gameState);

    assertEquals(0, board.symbolPlacedInPosition);
  }
}
