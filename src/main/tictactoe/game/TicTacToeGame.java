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
        notification.turnResult = "user_input_required";
      else if (result == POSITION_TAKEN)
        notification.turnResult = "position_taken";
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
    notification.gameState = "playing";
    notification.availablePositions = board.getAvailablePositions();
    return notification;
  }

  private void updateBoardWithCurrentAndNextPlayersForTurn() {
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(nextPlayer());
  }

  private void updateNotificationAndEndTurn(TicTacToeTurnNotification notification) {
    notification.turnResult = "turn_complete";
    notification.lastPositionPlayed = board.getLastPositionPlayed();
    updateNotificationAndEndGameIfGameIsOver(notification);
    currentPlayer = nextPlayer();
  }

  private void updateNotificationAndEndGameIfGameIsOver(TicTacToeTurnNotification notification) {
    if (board.gameIsWon() || board.gameIsTied()) {
      notification.gameState = "game_over";
      notification.gameResult = board.gameIsTied() ? "tied_game" : "winner";
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
    ((ControllablePlayer) players[currentPlayer]).receiveInput(input);
  }

  enum GameState {ENDED, PLAYING}
}
