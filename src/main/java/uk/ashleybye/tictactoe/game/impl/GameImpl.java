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
  Player[] players;
  private GamePlayBoundary gamePlayBoundary;
  private String gameStatus;
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
    GameState gameState = new GameState();
    while (!(board.gameIsWon() || board.gameIsTied())) {
      initialiseGameStateForTurn(gameState);
      players[currentPlayer].playTurn(gameState);
      currentPlayer = nextPlayer();
    }
    endGame(gameState);
    return true;
  }

  private void initialiseGameStateForTurn(GameState gameState) {
    gameState.setBoard(board);
    gameState.setCurrentPlayer(currentPlayer);
    gameState.setNextPlayer(nextPlayer());
    gameState.setPlayers(players);
    gameState.setGameStatus(GAME_STATUS_PLAYING);
  }

  private void endGame(GameState gameState) {
    gameState.setGameStatus(GAME_STATUS_GAME_OVER);
    gameState.setGameResult(board.gameIsTied() ? GAME_RESULT_TIED_GAME : GAME_RESULT_WINNER);
    gamePlayBoundary.updateDisplay(gameState);
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
