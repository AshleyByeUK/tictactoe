package uk.ashleybye.tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.tictactoe.game.TicTacToeGame.GameStatus.ENDED;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.Game;
import uk.ashleybye.tictactoe.Player;
import uk.ashleybye.tictactoe.TurnNotification;
import uk.ashleybye.tictactoe.player.computer.ComputerPlayer;
import uk.ashleybye.tictactoe.player.computer.HardArtificialIntelligence;
import uk.ashleybye.tictactoe.player.computer.MediumArtificialIntelligence;
import uk.ashleybye.tictactoe.player.human.HumanPlayer;
import uk.ashleybye.tictactoe.ui.console.UserInterfaceMock;

public class TicTacToeGameTest extends TicTacToeTurnNotificationPublisher {

  private boolean publisherShouldEndGame;
  private TurnNotification notification;
  private TicTacToeGame game;
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
    Player player1 = new HumanPlayer("player1", userInterfaceMock);
    Player player2 = new HumanPlayer("player2", userInterfaceMock);
    game = Game.playTicTacToe(player1, player2, 0);
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
    Player computer1 = new ComputerPlayer("computer1", new MediumArtificialIntelligence());
    Player computer2 = new ComputerPlayer("computer2", new HardArtificialIntelligence());
    game = new TicTacToeGame(computer1, computer2);
    game.play(this);

    assertEquals("game_over", notification.gameState);
    assertTrue(game.board.gameIsWon() || game.board.gameIsTied());
  }
}
