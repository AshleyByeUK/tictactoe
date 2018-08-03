package uk.ashleybye.tictactoe.tictactoe.player.human;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.tictactoe.GameState;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeBoardMock;
import uk.ashleybye.tictactoe.ui.console.UserInterfaceMock;

class HumanPlayerTest {

  private HumanPlayer player;
  private UserInterfaceMock userInterfaceMock;

  @BeforeEach
  void setUp() {
    userInterfaceMock = new UserInterfaceMock();
    player = new HumanPlayer("human", userInterfaceMock);
  }

  @Test
  void validMovePlacesSymbolOnBoard() {
    userInterfaceMock.setPlayerPositionsToPlay(4);
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard();
    GameState gameState = new GameState(boardMock, 1, 0);
    player.playTurn(gameState);

    assertTrue(boardMock.placeSymbolAtPositionWasCalled);
  }

  @Test
  void turnEndsAfterValidPositionPlayed() {
    userInterfaceMock.setPlayerPositionsToPlay(1, 2);
    TicTacToeBoardMock boardMock = TicTacToeBoardMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1});
    GameState gameState = new GameState(boardMock, 1, 0);
    player.playTurn(gameState);

    assertArrayEquals(new int[]{0, 1, -1, -1, -1, -1, -1, -1, -1}, boardMock.getPositions());
  }
}
