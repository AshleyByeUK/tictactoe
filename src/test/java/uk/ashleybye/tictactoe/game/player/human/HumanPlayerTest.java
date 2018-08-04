package uk.ashleybye.tictactoe.game.player.human;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.impl.BoardImplMock;
import uk.ashleybye.tictactoe.ui.console.ConsoleGamePlayBoundaryMock;

class HumanPlayerTest {

  private HumanPlayer player;
  private ConsoleGamePlayBoundaryMock userInterfaceMock;
  private Player[] players;
  private GameState gameState;

  @BeforeEach
  void setUp() {
    userInterfaceMock = new ConsoleGamePlayBoundaryMock();
    player = new HumanPlayer("human", "X", userInterfaceMock);
    players = new Player[]{player, player};
    gameState = new GameState();
    gameState.setCurrentPlayer(1);
    gameState.setNextPlayer(0);
    gameState.setPlayers(players);
  }

  @Test
  void validMovePlacesSymbolOnBoard() {
    userInterfaceMock.setPlayerPositionsToPlay(4);
    BoardImplMock boardMock = BoardImplMock.configureBoard();
    gameState.setBoard(boardMock);
    player.playTurn(gameState);

    assertTrue(boardMock.placeSymbolAtPositionWasCalled);
  }

  @Test
  void turnEndsAfterValidPositionPlayed() {
    userInterfaceMock.setPlayerPositionsToPlay(1, 2);
    BoardImplMock boardMock = BoardImplMock.configureBoard(new int[]{0, -1, -1, -1, -1, -1, -1, -1, -1});
    gameState.setBoard(boardMock);
    player.playTurn(gameState);

    assertArrayEquals(new int[]{0, 1, -1, -1, -1, -1, -1, -1, -1}, boardMock.getPositions());
  }
}
