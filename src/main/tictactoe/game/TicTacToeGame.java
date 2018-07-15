package tictactoe.game;

import static tictactoe.game.TicTacToeGame.GameState.ENDED;
import static tictactoe.game.TicTacToeGame.GameState.PLAYING;
import static tictactoe.player.TurnResult.INPUT_REQUIRED;
import static tictactoe.player.TurnResult.POSITION_TAKEN;

import tictactoe.ControllablePlayer;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.player.TurnResult;

public class TicTacToeGame implements Game<TicTacToeTurnNotificationPublisher> {

  public static final String TURN_RESULT_USER_INPUT_REQUIRED = "user_input_required";
  public static final String TURN_RESULT_POSITION_TAKEN = "position_taken";
  public static final String TURN_RESULT_TURN_COMPLETE = "turn_complete";
  public static final String GAME_STATE_PLAYING = "playing";
  public static final String GAME_STATE_GAME_OVER = "game_over";
  public static final String GAME_RESULT_TIED_GAME = "tied_game";
  public static final String GAME_RESULT_WINNER = "winner";

  TicTacToeBoard board;
  GameState gameState;
  private Player[] players;
  private int currentPlayer;

  public TicTacToeGame(Player player1, Player player2) {
    board = new TicTacToeBoard();
    players = new Player[]{player1, player2};
    currentPlayer = 0;
    gameState = PLAYING;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
  }

  @Override
  public boolean play(TicTacToeTurnNotificationPublisher publisher) {
    while (gameState == PLAYING) {
      TicTacToeTurnNotification notification = initialiseNotificationForTurn();
      updateBoardWithCurrentAndNextPlayersForTurn();
      TurnResult result = players[currentPlayer].playTurn(board);

      if (result == INPUT_REQUIRED)
        notification.turnResult = TURN_RESULT_USER_INPUT_REQUIRED;
      else if (result == POSITION_TAKEN)
        notification.turnResult = TURN_RESULT_POSITION_TAKEN;
      else
        updateNotificationAndEndTurn(notification);

      publisher.notify(notification);
    }

    return true;
  }

  private TicTacToeTurnNotification initialiseNotificationForTurn() {
    TicTacToeTurnNotification notification = new TicTacToeTurnNotification();
    notification.currentPlayerName = players[currentPlayer].getName();
    notification.board = board.getPositions();
    notification.gameState = GAME_STATE_PLAYING;
    notification.availablePositions = board.getAvailablePositions();
    return notification;
  }

  private void updateBoardWithCurrentAndNextPlayersForTurn() {
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(nextPlayer());
  }

  private void updateNotificationAndEndTurn(TicTacToeTurnNotification notification) {
    notification.turnResult = TURN_RESULT_TURN_COMPLETE;
    notification.lastPositionPlayed = board.getLastPositionPlayed();
    updateNotificationAndEndGameIfGameIsOver(notification);
    currentPlayer = nextPlayer();
  }

  private void updateNotificationAndEndGameIfGameIsOver(TicTacToeTurnNotification notification) {
    if (board.gameIsWon() || board.gameIsTied()) {
      notification.gameState = GAME_STATE_GAME_OVER;
      notification.gameResult = board.gameIsTied() ? GAME_RESULT_TIED_GAME : GAME_RESULT_WINNER;
      gameState = ENDED;
    }
  }

  private int nextPlayer() {
    if (currentPlayer == 0)
      return 1;
    else
      return 0;
  }

  int getCurrentPlayer() {
    return currentPlayer;
  }

  @Override
  public void receiveUserInput(int input) {
    // This method is only called for a HumanPlayer.
    ((ControllablePlayer) players[currentPlayer]).receiveInput(input);
  }

  enum GameState {ENDED, PLAYING}
}
