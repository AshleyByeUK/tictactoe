package tictactoe.game;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tictactoe.game.TicTacToeGame.GameState.ENDED;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.TurnResponseModel;
import tictactoe.player.computer.ComputerPlayer;
import tictactoe.player.computer.HardArtificialIntelligence;
import tictactoe.player.computer.MediumArtificialIntelligence;
import tictactoe.player.human.HumanPlayer;

public class TicTacToeGameTest extends TicTacToeTurnPresenter {

  private boolean presenterShouldEndGame;
  private TicTacToeTurnResponseModel responseModel;
  private TicTacToeGame game;

  @Override
  public void present(TurnResponseModel responseModel) {
    this.responseModel = (TicTacToeTurnResponseModel) responseModel;
    game.gameState = presenterShouldEndGame ? ENDED : game.gameState;
  }

  @BeforeEach
  void setUp() {
    presenterShouldEndGame = true;
    Player player1 = new HumanPlayer("player1");
    Player player2 = new HumanPlayer("player2");
    game = (TicTacToeGame) Game.playTicTacToe(player1, player2, 0);
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
  void gameWaitsForValidPlayerPosition() {
    game.board.getPositions()[0] = 1;
    game.receiveUserInput(0);
    game.play(this);

    assertEquals(0, responseModel.currentPlayer);
    assertEquals("player1", responseModel.currentPlayerName);
    assertEquals("position_taken", responseModel.turnResult);
    assertEquals("playing", responseModel.gameState);
    assertArrayEquals(new int[]{1, -1, -1, -1, -1, -1, -1, -1, -1}, responseModel.board);
    assertEquals(0, game.getCurrentPlayer());
  }

  @Test
  void gamePlayMovesToNextPlayerAfterValidTurn() {
    game.receiveUserInput(4);
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
    game.receiveUserInput(8);
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
    game.receiveUserInput(2);
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
    game = new TicTacToeGame(computer1, computer2);
    game.play(this);

    assertEquals("game_over", responseModel.gameState);
    assertTrue(game.board.gameIsWon() || game.board.gameIsTied());
  }
}
