package uk.ashleybye.tictactoe.tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.ashleybye.tictactoe.tictactoe.game.TicTacToeGame.GameStatus.ENDED;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.tictactoe.Game;
import uk.ashleybye.tictactoe.tictactoe.Player;
import uk.ashleybye.tictactoe.tictactoe.player.computer.ComputerPlayer;
import uk.ashleybye.tictactoe.tictactoe.player.computer.HardArtificialIntelligence;
import uk.ashleybye.tictactoe.tictactoe.player.computer.MediumArtificialIntelligence;
import uk.ashleybye.tictactoe.tictactoe.player.human.HumanPlayer;

public class TicTacToeGameTest extends TicTacToeTurnNotificationPublisher {

  private boolean publisherShouldEndGame;
  private TicTacToeTurnNotification notification;
  private TicTacToeGame game;

  @Override
  public void notify(TicTacToeTurnNotification notification) {
    this.notification = notification;
    game.gameStatus = publisherShouldEndGame ? ENDED : game.gameStatus;
  }

  @BeforeEach
  void setUp() {
    publisherShouldEndGame = true;
    Player player1 = new HumanPlayer("player1");
    Player player2 = new HumanPlayer("player2");
    game = Game.playTicTacToe(player1, player2, 0);
  }

  @Test
  void gameWaitsForPlayerInput() {
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertEquals("user_input_required", notification.turnResult);
    assertEquals("playing", notification.gameState);
    assertArrayEquals(new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1}, notification.board);
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void playerTwoCanGoFirst() {
    game.setFirstPlayer(1);
    game.play(this);

    assertEquals("player2", notification.currentPlayerName);
    assertEquals(1, game.getCurrentPlayer());
  }

  @Test
  void gameWaitsForValidPlayerPosition() {
    game.board.getPositions()[0] = 1;
    game.receiveUserInput(0);
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertEquals("position_taken", notification.turnResult);
    assertEquals("playing", notification.gameState);
    assertArrayEquals(new int[]{1, -1, -1, -1, -1, -1, -1, -1, -1}, notification.board);
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void gamePlayMovesToNextPlayerAfterValidTurn() {
    game.receiveUserInput(4);
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertEquals("turn_complete", notification.turnResult);
    assertEquals("playing", notification.gameState);
    assertArrayEquals(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1}, notification.board);
    assertEquals(1, game.getCurrentPlayer());
  }

  @Test
  void gameEndsWhenPlayersAreTied() {
    publisherShouldEndGame = false;
    game.board.positions = new int[]{0, 1, 0, 0, 1, 1, 1, 0, -1};
    game.receiveUserInput(8);
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertEquals("turn_complete", notification.turnResult);
    assertEquals("game_over", notification.gameState);
    assertEquals("tied_game", notification.gameResult);
    assertArrayEquals(new int[]{0, 1, 0, 0, 1, 1, 1, 0, 0}, notification.board);
  }

  @Test
  void gameEndsWhenPlayerWins() {
    game.board.positions = new int[]{0, 0, -1, 1, 1, -1, -1, -1, -1};
    game.receiveUserInput(2);
    game.play(this);

    assertEquals("player1", notification.currentPlayerName);
    assertEquals("turn_complete", notification.turnResult);
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
