package human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.PlayerResponse.POSITION_TAKEN;
import static tictactoe.PlayerResponse.TURN_COMPLETE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.BoardMock;
import tictactoe.PlayerResponse;

class HumanPlayerTest {

  private HumanPlayer player;

  @BeforeEach
  void setUp() {
    player = new HumanPlayer("human");
  }

  @Test
  void validMovePlacesTokenOnBoard() {
    BoardMock boardMock = BoardMock.configureBoard();
    player.receiveInput(4);
    PlayerResponse result = player.playTurn(boardMock);

    assertTrue(boardMock.placeTokenWasCalled);
    assertEquals(TURN_COMPLETE, result);
  }

  @Test
  void informsWhenInputIsInvalid() {
    BoardMock boardMock = BoardMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1}, 1);
    player.receiveInput(0);
    PlayerResponse result = player.playTurn(boardMock);

    assertEquals(POSITION_TAKEN, result);
  }

  @Test
  void informsWhenUSerInputIsRequire() {
    BoardMock boardMock = BoardMock.configureBoard();
    PlayerResponse result = player.playTurn(boardMock);

    assertEquals(INPUT_REQUIRED, result);
  }
}
