package uk.ashleybye.tictactoe.game.impl;


import static uk.ashleybye.tictactoe.game.impl.GameImpl.GameStatus.ENDED;
import static uk.ashleybye.tictactoe.game.impl.GameImpl.GameStatus.PLAYING;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.TurnNotificationPublisher;

public class GameImpl implements Game {

  public static final String GAME_STATUS_PLAYING = "playing";
  public static final String GAME_STATUS_GAME_OVER = "game_over";
  public static final String GAME_RESULT_TIED_GAME = "tied_game";
  public static final String GAME_RESULT_WINNER = "winner";

  BoardImpl board;
  GameStatus gameStatus;
  private Player[] players;
  private int currentPlayer;

  public GameImpl(Player player1, Player player2) {
    board = new BoardImpl();
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

      GameState gameState = new GameState(board, currentPlayer, nextPlayer(), players);
      players[currentPlayer].playTurn(gameState);
      updateNotificationAndEndTurn(notification);

      publisher.notify(notification);
    }

    return true;
  }

  private TurnNotification initialiseNotificationForTurn() {
    TurnNotification notification = new TurnNotification();
    notification.players = players;
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
