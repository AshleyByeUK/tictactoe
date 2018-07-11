package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static tictactoe.TurnResult.*;

import console.ConsoleUIMock;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.BoardSpy;
import tictactoe.CentreTokenBoardSpy;
import tictactoe.Player;
import tictactoe.TurnResult;
import tictactoe.TyingBoardSpy;
import tictactoe.WinningBoardSpy;

class ComputerPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private Player player;
  private BoardSpy boardSpy;

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    Random randomStub = new RandomStub();
    player = new ComputerPlayer("computer", randomStub);
  }

  @Test
  void choosesCentreSpotIfAvailable() {
    boardSpy = new BoardSpy();
    player.playTurn(boardSpy, mockConsoleUI);

    assertEquals(4, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    boardSpy = new WinningBoardSpy();
    player.playTurn(boardSpy, mockConsoleUI);

    assertEquals(2, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    boardSpy = new TyingBoardSpy();
    player.playTurn(boardSpy, mockConsoleUI);

    assertEquals(2, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    boardSpy = new CentreTokenBoardSpy();
    player.playTurn(boardSpy, mockConsoleUI);

    assertEquals(0, boardSpy.tokenPlacedInPosition);
  }

  @Test
  void informsWhenTurnIsComplete() {
    boardSpy = new BoardSpy();
    TurnResult result = player.playTurn(boardSpy, mockConsoleUI);

    assertEquals(TURN_COMPLETE, result);
  }
}
