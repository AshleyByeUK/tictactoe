package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tictactoe.PlayerResponse.TURN_COMPLETE;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.BoardMock;
import tictactoe.Player;
import tictactoe.PlayerResponse;

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
    BoardMock board = BoardMock.configureBoard();
    player.playTurn(board);

    assertEquals(4, board.tokenPlacedInPosition);
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    BoardMock board = BoardMock.configureBoard(new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1}, 1);
    player.playTurn(board);

    assertEquals(2, board.tokenPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    BoardMock board = BoardMock.configureBoard(new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0}, 1);
    player.playTurn(board);

    assertEquals(2, board.tokenPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    BoardMock board = BoardMock.configureBoard(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1}, 1);
    player.playTurn(board);

    assertEquals(0, board.tokenPlacedInPosition);
  }

  @Test
  void informsWhenTurnIsComplete() {
    BoardMock board = BoardMock.configureBoard();
    PlayerResponse result = player.playTurn(board);

    assertEquals(TURN_COMPLETE, result);
  }
}
