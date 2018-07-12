package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tictactoe.PlayerResponse.TURN_COMPLETE;

import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.BoardSpy;
import tictactoe.CentreTokenBoardSpy;
import tictactoe.Player;
import tictactoe.PlayerResponse;
import tictactoe.TyingBoardSpy;
import tictactoe.WinningBoardSpy;

class MediumComputerPlayerTest {

  private Player player;
  private BoardSpy boardSpy;

  @BeforeEach
  void setUp() {
    Random randomStub = new RandomStub();
    player = new MediumComputerPlayer("computer", randomStub);
  }

  @Test
  void choosesCentreSpotIfAvailable() {
    boardSpy = new BoardSpy();
    player.playTurn(boardSpy);

    assertEquals(4, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    boardSpy = new WinningBoardSpy();
    player.playTurn(boardSpy);

    assertEquals(2, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    boardSpy = new TyingBoardSpy();
    player.playTurn(boardSpy);

    assertEquals(2, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    boardSpy = new CentreTokenBoardSpy();
    player.playTurn(boardSpy);

    assertEquals(0, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void informsWhenTurnIsComplete() {
    boardSpy = new BoardSpy();
    PlayerResponse result = player.playTurn(boardSpy);

    assertEquals(TURN_COMPLETE, result);
  }
}
