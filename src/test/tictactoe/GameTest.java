package tictactoe;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.Game.GameState.ENDED;

import computer.ComputerPlayer;
import computer.HardArtificialIntelligence;
import computer.MediumArtificialIntelligence;
import human.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest extends TurnPresenter {

  private boolean presenterShouldEndGame;
  private TurnResponseModel responseModel;
  private Player player1;
  private Player player2;
  private Game game;

  @Override
  public void present(TurnResponseModel responseModel) {
    this.responseModel = responseModel;
    game.gameState = presenterShouldEndGame ? ENDED : game.gameState;
  }

  @BeforeEach
  void setUp() {
    presenterShouldEndGame = true;
    player1 = new HumanPlayer("player1");
    player2 = new HumanPlayer("player2");
    game = new Game(player1, player2);
  }

  @Test
  void setsCurrentAndNextPlayerOnBoard() {
    game.play(this);

    assertEquals(0, game.board.getCurrentPlayer());
    assertEquals(1, game.board.getNextPlayer());
  }

  @Test
  void gameWaitsForPlayerInput() {
    game.play(this);

    assertEquals(0, responseModel.currentPlayer);
    assertEquals("player1", responseModel.currentPlayerName);
    assertEquals("user_input_required", responseModel.turnResult);
    assertEquals("playing", responseModel.gameState);
    assertArrayEquals(new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1}, responseModel.board);
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void playerTwoCanGoFirst() {
    game.setFirstPlayer(1);
    game.play(this);

    assertEquals(1, responseModel.currentPlayer);
    assertEquals("player2", responseModel.currentPlayerName);
    assertEquals(1, game.getCurrentPlayer());
  }

  @Test
  void gameWaitsForValidPlayerInput() {
    game.receiveUserInput("invalid");
    game.play(this);

    assertEquals(0, responseModel.currentPlayer);
    assertEquals("player1", responseModel.currentPlayerName);
    assertEquals("user_input_invalid", responseModel.turnResult);
    assertEquals("playing", responseModel.gameState);
    assertArrayEquals(new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1}, responseModel.board);
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void gamePlayMovesToNextPlayerAfterValidTurn() {
    game.receiveUserInput("4");
    game.play(this);

    assertEquals(0, responseModel.currentPlayer);
    assertEquals("player1", responseModel.currentPlayerName);
    assertEquals("turn_complete", responseModel.turnResult);
    assertEquals("playing", responseModel.gameState);
    assertArrayEquals(new int[]{-1, -1, -1, -1, 0, -1, -1, -1, -1}, responseModel.board);
    assertEquals(1, game.getCurrentPlayer());
  }

  @Test
  void gameEndsWhenPlayersAreTied() {
    presenterShouldEndGame = false;
    game.board.positions = new int[]{0, 1, 0, 0, 1, 1, 1, 0, -1};
    game.receiveUserInput("8");
    game.play(this);

    assertEquals(0, responseModel.currentPlayer);
    assertEquals("player1", responseModel.currentPlayerName);
    assertEquals("turn_complete", responseModel.turnResult);
    assertEquals("game_over", responseModel.gameState);
    assertEquals("tied_game", responseModel.gameResult);
    assertArrayEquals(new int[]{0, 1, 0, 0, 1, 1, 1, 0, 0}, responseModel.board);
  }

  @Test
  void gameEndsWhenPlayerWins() {
    game.board.positions = new int[]{0, 0, -1, 1, 1, -1, -1, -1, -1};
    game.receiveUserInput("2");
    game.play(this);

    assertEquals(0, responseModel.currentPlayer);
    assertEquals("player1", responseModel.currentPlayerName);
    assertEquals("turn_complete", responseModel.turnResult);
    assertEquals("game_over", responseModel.gameState);
    assertEquals("winner", responseModel.gameResult);
    assertArrayEquals(new int[]{0, 0, 0, 1, 1, -1, -1, -1, -1}, responseModel.board);
  }

  @Test
  void computerCanPlayAgainstComputer() {
    presenterShouldEndGame = false;
    Player computer1 = new ComputerPlayer("computer1", new MediumArtificialIntelligence());
    Player computer2 = new ComputerPlayer("computer2", new HardArtificialIntelligence());
    game = new Game(computer1, computer2);
    game.play(this);

    assertEquals("game_over", responseModel.gameState);
    assertTrue(game.board.gameIsWon() || game.board.gameIsTied());
  }
}
