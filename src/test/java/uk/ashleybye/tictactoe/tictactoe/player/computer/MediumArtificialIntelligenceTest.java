package uk.ashleybye.tictactoe.tictactoe.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.TURN_COMPLETE;


import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.tictactoe.Player;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeBoardMock;
import uk.ashleybye.tictactoe.tictactoe.player.TurnResult;

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
    player.playTurn(board);

    assertEquals(4, board.symbolPlacedInPosition);
  }

  @Test
  void choosesWinningPositionIfAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1}, 1);
    player.playTurn(board);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0}, 1);
    player.playTurn(board);

    assertEquals(2, board.symbolPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1}, 1);
    player.playTurn(board);

    assertEquals(0, board.symbolPlacedInPosition);
  }

  @Test
  void informsWhenTurnIsComplete() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard();
    TurnResult result = player.playTurn(board);

    assertEquals(TURN_COMPLETE, result);
  }
}
