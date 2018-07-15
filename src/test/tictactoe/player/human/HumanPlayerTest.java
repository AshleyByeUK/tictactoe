package tictactoe.player.human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.player.TurnResult.INPUT_REQUIRED;
import static tictactoe.player.TurnResult.POSITION_TAKEN;
import static tictactoe.player.TurnResult.TURN_COMPLETE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.game.TicTacToeBoardMock;
import tictactoe.player.TurnResult;

class HumanPlayerTest {

  private HumanPlayer player;

  @BeforeEach
  void setUp() {
    player = new HumanPlayer("human");
  }

  @Test
  void validMovePlacesSymbolOnBoard() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    player.receiveInput(4);
    TurnResult result = player.playTurn(boardMock);

    assertTrue(boardMock.placeSymbolAtPositionWasCalled);
    assertEquals(TURN_COMPLETE, result);
  }

  @Test
  void informsWhenInputIsInvalid() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1}, 1);
    player.receiveInput(0);
    TurnResult result = player.playTurn(boardMock);

    assertEquals(POSITION_TAKEN, result);
  }

  @Test
  void informsWhenUserInputIsRequired() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    TurnResult result = player.playTurn(boardMock);

    assertEquals(INPUT_REQUIRED, result);
  }
}