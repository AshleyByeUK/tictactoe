package uk.ashleybye.tictactoe.tictactoe.player.human;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.INPUT_REQUIRED;
import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.POSITION_TAKEN;
import static uk.ashleybye.tictactoe.tictactoe.player.TurnResult.TURN_COMPLETE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.tictactoe.GameState;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeBoardMock;
import uk.ashleybye.tictactoe.tictactoe.player.TurnResult;

class HumanPlayerTest {

  private HumanPlayer player;

  @BeforeEach
  void setUp() {
    player = new HumanPlayer("human");
  }

  @Test
  void validMovePlacesSymbolOnBoard() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    GameState gameState = new GameState(boardMock, 1, 0);
    player.receiveInput(4);
    TurnResult result = player.playTurn(gameState);

    assertTrue(boardMock.placeSymbolAtPositionWasCalled);
    assertEquals(TURN_COMPLETE, result);
  }

  @Test
  void informsWhenInputIsInvalid() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1});
    GameState gameState = new GameState(boardMock, 1, 0);
    player.receiveInput(0);
    TurnResult result = player.playTurn(gameState);

    assertEquals(POSITION_TAKEN, result);
  }

  @Test
  void informsWhenUserInputIsRequired() {
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    GameState gameState = new GameState(boardMock, 1, 0);
    TurnResult result = player.playTurn(gameState);

    assertEquals(INPUT_REQUIRED, result);
  }
}
