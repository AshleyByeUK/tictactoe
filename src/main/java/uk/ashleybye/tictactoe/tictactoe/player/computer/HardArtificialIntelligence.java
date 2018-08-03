package uk.ashleybye.tictactoe.tictactoe.player.computer;


import uk.ashleybye.tictactoe.tictactoe.Board;
import uk.ashleybye.tictactoe.tictactoe.GameState;

public class HardArtificialIntelligence implements ArtificialIntelligence {

  private int currentPlayer;
  private int nextPlayer;
  private int thisPlayer;
  private int otherPlayer;

  @Override
  public int computeNextMove(GameState gameState) {
    currentPlayer = gameState.getCurrentPlayer();
    nextPlayer = gameState.getNextPlayer();
    thisPlayer = currentPlayer;
    otherPlayer = nextPlayer;
    return minimax(gameState.getBoard()).bestPosition;
  }

  private MinimaxResult minimax(Board board) {
    int bestScore = (currentPlayer == thisPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    int bestPosition = -1;
    MinimaxResult bestResult = new MinimaxResult(bestScore, bestPosition);

    if (board.gameIsWon() || board.gameIsTied())
      bestResult.bestScore = computeScore(board);
    else
      computeBestNextTurn(board, bestResult);

    return bestResult;
  }

  private int computeScore(Board board) {
    int lastPlayer = nextPlayer;
    if (board.gameIsWon() && lastPlayer == thisPlayer)
      return 100;
    else if (board.gameIsWon() && lastPlayer == otherPlayer)
      return -100;
    else
      return 0;
  }

  private void computeBestNextTurn(Board board, MinimaxResult bestResult) {
    for (int ap : board.getAvailablePositions()) {
      board.placeSymbolAtPosition(ap, currentPlayer);
      if (currentPlayer == thisPlayer)
        maximise(ap, board, bestResult);
      else
        minimise(ap, board, bestResult);
      board.getPositions()[ap] = -1;
    }
  }

  private void maximise(int pos, Board board, MinimaxResult bestResult) {
    swapPlayerOrder();
    int currentScore = minimax(board).bestScore;
    swapPlayerOrder();
    if (currentScore > bestResult.bestScore) {
      bestResult.bestScore = currentScore;
      bestResult.bestPosition = pos;
    }
  }

  private void minimise(int pos, Board board, MinimaxResult bestResult) {
    swapPlayerOrder();
    int currentScore = minimax(board).bestScore;
    swapPlayerOrder();
    if (currentScore < bestResult.bestScore) {
      bestResult.bestScore = currentScore;
      bestResult.bestPosition = pos;
    }
  }

  private void swapPlayerOrder() {
    int temp = currentPlayer;
    currentPlayer = nextPlayer;
    nextPlayer = temp;
  }

  private class MinimaxResult {

    private int bestScore;
    private int bestPosition;

    private MinimaxResult(int bestScore, int bestPosition) {
      this.bestScore = bestScore;
      this.bestPosition = bestPosition;
    }
  }
}
