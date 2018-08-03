package uk.ashleybye.tictactoe.game;


import static uk.ashleybye.tictactoe.game.TicTacToeGame.GameStatus.ENDED;
import static uk.ashleybye.tictactoe.game.TicTacToeGame.GameStatus.PLAYING;

import uk.ashleybye.tictactoe.Game;
import uk.ashleybye.tictactoe.GameState;
import uk.ashleybye.tictactoe.Player;
import uk.ashleybye.tictactoe.TurnNotification;
import uk.ashleybye.tictactoe.TurnNotificationPublisher;

public class TicTacToeGame implements Game {

  public static final String GAME_STATUS_PLAYING = "playing";
  public static final String GAME_STATUS_GAME_OVER = "game_over";
  public static final String GAME_RESULT_TIED_GAME = "tied_game";
  public static final String GAME_RESULT_WINNER = "winner";

  TicTacToeBoard board;
  GameStatus gameStatus;
  private Player[] players;
  private int currentPlayer;

  public TicTacToeGame(Player player1, Player player2) {
    board = new TicTacToeBoard();
    players = new Player[]{player1, player2};
    currentPlayer = 0;
    gameStatus = PLAYING;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
  }

  @Override
  public boolean play(TurnNotificationPublisher publisher) {
    while (gameStatus == PLAYING) {
      TurnNotification notification = initialiseNotificationForTurn();

      GameState gameState = new GameState(board, currentPlayer, nextPlayer());
      players[currentPlayer].playTurn(gameState);
      updateNotificationAndEndTurn(notification);

      publisher.notify(notification);
    }

    return true;
  }

  private TurnNotification initialiseNotificationForTurn() {
    TurnNotification notification = new TurnNotification();
    notification.currentPlayerName = players[currentPlayer].getName();
    notification.board = board.getPositions();
    notification.gameState = GAME_STATUS_PLAYING;
    notification.availablePositions = board.getAvailablePositions();
    return notification;
  }

  private void updateNotificationAndEndTurn(TurnNotification notification) {
    notification.lastPositionPlayed = board.getLastPositionPlayed();
    updateNotificationAndEndGameIfGameIsOver(notification);
    currentPlayer = nextPlayer();
  }

  private void updateNotificationAndEndGameIfGameIsOver(TurnNotification notification) {
    if (board.gameIsWon() || board.gameIsTied()) {
      notification.gameState = GAME_STATUS_GAME_OVER;
      notification.gameResult = board.gameIsTied() ? GAME_RESULT_TIED_GAME : GAME_RESULT_WINNER;
      gameStatus = ENDED;
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

  enum GameStatus {ENDED, PLAYING}
}
