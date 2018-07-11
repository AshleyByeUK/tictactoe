package human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.TurnResult.*;
import static tictactoe.TurnResult.TURN_COMPLETE;

import console.ConsoleUIMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.Board;
import tictactoe.BoardSpy;
import tictactoe.TurnResult;

class HumanPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private HumanPlayer player;
  private Board board;
  private BoardSpy boardSpy;

  private void assertInvalidInput(String input) {
    player.receiveInput(input);
    TurnResult result = player.playTurn(board, mockConsoleUI);

    assertEquals(INVALID_INPUT, result);
  }

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    player = new HumanPlayer("human");
    board = new Board();
    boardSpy = new BoardSpy();
  }

  @Test
  void invalidUserInput() {
    assertInvalidInput("");
    assertInvalidInput(" ");
    assertInvalidInput("A");
    assertInvalidInput("£");
    assertInvalidInput("invalid input");
    assertInvalidInput("12 34");
  }

  @Test
  void outOfRangeUserInput() {
    assertInvalidInput("-1");
    assertInvalidInput("9");
  }

  @Test
  void validMovePlacesTokenOnBoard() {
    player.receiveInput("4");
    TurnResult result = player.playTurn(boardSpy, mockConsoleUI);

    assertTrue(boardSpy.placeTokenCalled);
    assertEquals(TURN_COMPLETE, result);
  }

  @Test
  void informsWhenUSerInputIsRequire() {
    TurnResult result = player.playTurn(boardSpy, mockConsoleUI);

    assertEquals(INPUT_REQUIRED, result);
  }
}
