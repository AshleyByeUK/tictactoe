package tictactoe.player.computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tictactoe.player.PlayerResponse.TURN_COMPLETE;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.game.TicTacToeBoardMock;
import tictactoe.Player;
import tictactoe.player.PlayerResponse;

class MediumArtificialIntelligenceTest {

  private Player player;

  @BeforeEach
  void setUp() {
    Random randomStub = new RandomStub();
    ArtificialIntelligence ai = new MediumArtificialIntelligence(randomStub);
    player = new ComputerPlayer("computer", ai);
  }

  @Test
  void choosesCentreSpotIfAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard();
    player.playTurn(board);

    assertEquals(4, board.tokenPlacedInPosition);
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1}, 1);
    player.playTurn(board);

    assertEquals(2, board.tokenPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0}, 1);
    player.playTurn(board);

    assertEquals(2, board.tokenPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1}, 1);
    player.playTurn(board);

    assertEquals(0, board.tokenPlacedInPosition);
  }

  @Test
  void informsWhenTurnIsComplete() {
    TicTacToeBoardMock board = TicTacToeBoardMock.configureBoard();
    PlayerResponse result = player.playTurn(board);

    assertEquals(TURN_COMPLETE, result);
  }
}
