package uk.ashleybye.tictactoe.game.impl;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.tictactoe.game.impl.GameImpl.GameStatus.ENDED;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.player.computer.ComputerPlayer;
import uk.ashleybye.tictactoe.game.player.computer.HardArtificialIntelligence;
import uk.ashleybye.tictactoe.game.player.computer.MediumArtificialIntelligence;
import uk.ashleybye.tictactoe.game.player.human.HumanPlayer;
import uk.ashleybye.tictactoe.ui.console.UserInterfaceMock;

public class GameImplTest extends TurnNotificationPublisherImpl {

  private boolean publisherShouldEndGame;
  private TurnNotification notification;
  private GameImpl game;
  private UserInterfaceMock userInterfaceMock;

  @Override
  public void notify(TurnNotification notification) {
    this.notification = notification;
    game.gameStatus = publisherShouldEndGame ? ENDED : game.gameStatus;
  }

  @BeforeEach
  void setUp() {
    publisherShouldEndGame = true;
    userInterfaceMock = new UserInterfaceMock();
    Player player1 = new HumanPlayer("player1", "X", userInterfaceMock);
    Player player2 = new HumanPlayer("player2", "O", userInterfaceMock);
    game = new GameImpl(player1, player2);
  }

  @Test
  void playerTwoCanGoFirst() {
    userInterfaceMock.setPlayerPositionsToPlay(1);
    game.setFirstPlayer(1);
    game.play(this);

    assertEquals("player2", notification.currentPlayerName);
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void gameEndsWhenPlayersAreTied() {
    publisherShouldEndGame = false;
    userInterfaceMock.setPlayerPositionsToPlay(9);
    game.board.positions = new int[]{0, 1, 0, 0, 1, 1, 1, 0, -1};
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertFalse(notification.userInputIsRequired);
    assertEquals("game_over", notification.gameState);
    assertEquals("tied_game", notification.gameResult);
    assertArrayEquals(new int[]{0, 1, 0, 0, 1, 1, 1, 0, 0}, notification.board);
  }

  @Test
  void gameEndsWhenPlayerWins() {
    userInterfaceMock.setPlayerPositionsToPlay(3);
    game.board.positions = new int[]{0, 0, -1, 1, 1, -1, -1, -1, -1};
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertFalse(notification.userInputIsRequired);
    assertEquals("game_over", notification.gameState);
    assertEquals("winner", notification.gameResult);
    assertArrayEquals(new int[]{0, 0, 0, 1, 1, -1, -1, -1, -1}, notification.board);
  }

  @Test
  void computerCanPlayAgainstComputer() {
    publisherShouldEndGame = false;
    Player computer1 = new ComputerPlayer("computer1", "X", new MediumArtificialIntelligence());
    Player computer2 = new ComputerPlayer("computer2", "O", new HardArtificialIntelligence());
    game = new GameImpl(computer1, computer2);
    game.play(this);

    assertEquals("game_over", notification.gameState);
    assertTrue(game.board.gameIsWon() || game.board.gameIsTied());
  }
}
