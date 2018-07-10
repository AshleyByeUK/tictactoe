package human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import console.ConsoleUIMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.Board;
import tictactoe.BoardSpy;

class HumanPlayerTest {

  private ConsoleUIMock mockConsoleUI;
  private HumanPlayer player;
  private Board board;

  private void assertInvalidInput(String input) {
    mockConsoleUI.setUserInputValue(input);
    player.validInput(input, board, mockConsoleUI);

    assertEquals("Invalid input", mockConsoleUI.lastMessage);
  }

  @BeforeEach
  void setUp() {
    mockConsoleUI = new ConsoleUIMock();
    player = new HumanPlayer("human");
    board = new Board();
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
    BoardSpy boardSpy = new BoardSpy();
    player.playTurn(boardSpy, mockConsoleUI);

    assertTrue(boardSpy.placeTokenCalled);
  }
}
