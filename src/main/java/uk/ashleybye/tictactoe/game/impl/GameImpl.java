package uk.ashleybye.tictactoe.game.impl;

import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.GameState;
import uk.ashleybye.tictactoe.game.Player;

public class GameImpl implements Game {

  public static final String GAME_STATUS_PLAYING = "playing";
  public static final String GAME_STATUS_GAME_OVER = "game_over";
  public static final String GAME_RESULT_TIED_GAME = "tied_game";
  public static final String GAME_RESULT_WINNER = "winner";

  BoardImpl board;
  String gameStatus;
  private Player[] players;
  private GamePlayBoundary gamePlayBoundary;
  private int currentPlayer;

  public GameImpl(Player player1, Player player2, GamePlayBoundary gamePlayBoundary) {
    this.gamePlayBoundary = gamePlayBoundary;
    board = new BoardImpl();
    players = new Player[]{player1, player2};
    currentPlayer = 0;
    gameStatus = GAME_STATUS_PLAYING;
  }

  public void setFirstPlayer(int player) {
    currentPlayer = player;
  }

  @Override
  public boolean play() {
    while (gameStatus.equals(GAME_STATUS_PLAYING)) {
      GameState gameState = initialiseGameStateForTurn();
      players[currentPlayer].playTurn(gameState);
      updateNotificationAndEndTurn(gameState);

      gamePlayBoundary.updateDisplay(gameState);
    }

    return true;
  }

  private GameState initialiseGameStateForTurn() {
    GameState gameState = new GameState(board, currentPlayer, nextPlayer(), players);
    gameState.setGameStatus(GAME_STATUS_PLAYING);
    return gameState;
  }

  private void updateNotificationAndEndTurn(GameState gameState) {
    updateNotificationAndEndGameIfGameIsOver(gameState);
    currentPlayer = nextPlayer();
  }

  private void updateNotificationAndEndGameIfGameIsOver(GameState gameState) {
    if (board.gameIsWon() || board.gameIsTied()) {
      gameStatus = GAME_STATUS_GAME_OVER;
      gameState.setGameStatus(gameStatus);
      String gameResult = board.gameIsTied() ? GAME_RESULT_TIED_GAME : GAME_RESULT_WINNER;
      gameState.setGameResult(gameResult);
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
}
