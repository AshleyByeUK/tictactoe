package tictactoe.player.human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.player.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.player.PlayerResponse.POSITION_TAKEN;
import static tictactoe.player.PlayerResponse.TURN_COMPLETE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.game.TicTacToeBoardMock;
import tictactoe.player.PlayerResponse;

class HumanPlayerTest {

  private HumanPlayer player;

  @BeforeEach
  void setUp() {
    player = new HumanPlayer("human");
  }

  @Test
  void validMovePlacesTokenOnBoard() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    player.receiveInput(4);
    PlayerResponse result = player.playTurn(boardMock);

    assertTrue(boardMock.placeTokenWasCalled);
    assertEquals(TURN_COMPLETE, result);
  }

  @Test
  void informsWhenInputIsInvalid() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1}, 1);
    player.receiveInput(0);
    PlayerResponse result = player.playTurn(boardMock);

    assertEquals(POSITION_TAKEN, result);
  }

  @Test
  void informsWhenUSerInputIsRequire() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    PlayerResponse result = player.playTurn(boardMock);

    assertEquals(INPUT_REQUIRED, result);
  }
}
