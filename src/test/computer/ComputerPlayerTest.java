package computer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import console.ConsoleUIMock;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.BoardSpy;
import tictactoe.CentreTokenBoardSpy;
import tictactoe.Player;
import tictactoe.TyingBoardSpy;
import tictactoe.WinningBoardSpy;

class ComputerPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private Player player;
  private BoardSpy boardStub;

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    Random randomStub = new RandomStub();
    player = new ComputerPlayer("computer", randomStub);
  }

  @Test
  void choosesCentreSpotIfAvailable() {
    boardStub = new BoardSpy();
    player.playTurn(boardStub, mockConsoleUI);

    assertEquals(4, boardStub.tokenPlacedInPosition);
  }

  @Test
  void choosesWinningSpotIfAvailable() {
    boardStub = new WinningBoardSpy();
    player.playTurn(boardStub, mockConsoleUI);

    assertEquals(2, boardStub.tokenPlacedInPosition);
  }

  @Test
  void stopsOppositionWinning() {
    boardStub = new TyingBoardSpy();
    player.playTurn(boardStub, mockConsoleUI);

    assertEquals(2, boardStub.tokenPlacedInPosition);
  }

  @Test
  void choosesRandomPositionIfNoWinningOrBlockingMoves() {
    boardStub = new CentreTokenBoardSpy();
    player.playTurn(boardStub, mockConsoleUI);

    assertEquals(0, boardStub.tokenPlacedInPosition);
  }
}
