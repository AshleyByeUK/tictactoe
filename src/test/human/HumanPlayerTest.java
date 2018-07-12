package human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.PlayerResponse.INVALID_INPUT;
import static tictactoe.PlayerResponse.TURN_COMPLETE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.Board;
import tictactoe.BoardMock;
import tictactoe.PlayerResponse;

class HumanPlayerTest {

  private HumanPlayer player;
  private Board board;

  private void assertInvalidInput(String input) {
    player.receiveInput(input);
    PlayerResponse result = player.playTurn(board);

    assertEquals(INVALID_INPUT, result);
  }

  @BeforeEach
  void setUp() {
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
    BoardMock boardMock = BoardMock.configureBoard();
    player.receiveInput("4");
    PlayerResponse result = player.playTurn(boardMock);

    assertTrue(boardMock.placeTokenWasCalled);
    assertEquals(TURN_COMPLETE, result);
  }

  @Test
  void informsWhenUSerInputIsRequire() {
    BoardMock boardMock = BoardMock.configureBoard();
    PlayerResponse result = player.playTurn(boardMock);

    assertEquals(INPUT_REQUIRED, result);
  }
}
