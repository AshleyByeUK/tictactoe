package uk.ashleybye.tictactoe.game.player.human;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.impl.BoardImplMock;
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
    BoardImplMock boardMock = BoardImplMock.configureBoard();
    GameState gameState = new GameState(boardMock, 1, 0);
    player.playTurn(gameState);

    assertTrue(boardMock.placeSymbolAtPositionWasCalled);
  }

  @Test
  void turnEndsAfterValidPositionPlayed() {
    userInterfaceMock.setPlayerPositionsToPlay(1, 2);
    BoardImplMock boardMock = BoardImplMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1});
    GameState gameState = new GameState(boardMock, 1, 0);
    player.playTurn(gameState);

    assertArrayEquals(new int[]{0, 1, -1, -1, -1, -1, -1, -1, -1}, boardMock.getPositions());
  }
}
