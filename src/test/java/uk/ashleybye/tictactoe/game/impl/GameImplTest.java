package uk.ashleybye.tictactoe.game.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.player.computer.ComputerPlayer;
import uk.ashleybye.tictactoe.game.player.computer.HardDifficulty;
import uk.ashleybye.tictactoe.game.player.computer.MediumDifficulty;
import uk.ashleybye.tictactoe.game.player.human.HumanPlayer;
import uk.ashleybye.tictactoe.ui.console.ConsoleGamePlayBoundaryMock;

class GameImplTest {

  private GameImpl game;
  private ConsoleGamePlayBoundaryMock boundaryMock;

  @BeforeEach
  void setUp() {
    boundaryMock = new ConsoleGamePlayBoundaryMock();
    Player player1 = new HumanPlayer("player1", "X", boundaryMock);
    Player player2 = new HumanPlayer("player2", "O", boundaryMock);
    game = new GameImpl(player1, player2, boundaryMock);
  }

  @Test
  void playerTwoCanGoFirst() {
    boundaryMock.setPlayerPositionsToPlay(1, 2, 3, 4, 5, 6, 7);
    game.setFirstPlayer(1);
    game.play();

    assertEquals(1, boundaryMock.gameState.getCurrentPlayer());
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void gameEndsWhenPlayersAreTied() {
    boundaryMock.setPlayerPositionsToPlay(9);
    game.board.positions = new int[]{0, 1, 0, 0, 1, 1, 1, 0, -1};
    game.play();

    GameState gameState = boundaryMock.gameState;
    assertEquals(0, gameState.getCurrentPlayer());
    assertFalse(gameState.isUserInputRequired());
    assertEquals("game_over", gameState.getGameStatus());
    assertEquals("tied_game", gameState.getGameResult());
    assertArrayEquals(new int[]{0, 1, 0, 0, 1, 1, 1, 0, 0}, gameState.getBoard().getPositions());
  }

  @Test
  void gameEndsWhenPlayerWins() {
    boundaryMock.setPlayerPositionsToPlay(3);
    game.board.positions = new int[]{0, 0, -1, 1, 1, -1, -1, -1, -1};
    game.play();

    GameState gameState = boundaryMock.gameState;
    assertEquals(0, gameState.getCurrentPlayer());
    assertFalse(gameState.isUserInputRequired());
    assertEquals("game_over", gameState.getGameStatus());
    assertEquals("winner", gameState.getGameResult());
    assertArrayEquals(new int[]{0, 0, 0, 1, 1, -1, -1, -1, -1}, gameState.getBoard().getPositions());
  }

  @Test
  void computerCanPlayAgainstComputer() {
    Player computer1 = new ComputerPlayer("computer1", "X", new MediumDifficulty());
    Player computer2 = new ComputerPlayer("computer2", "O", new HardDifficulty());
    game = new GameImpl(computer1, computer2, boundaryMock);
    game.play();

    assertEquals("game_over", boundaryMock.gameState.getGameStatus());
    assertTrue(game.board.gameIsWon() || game.board.gameIsTied());
  }
}
