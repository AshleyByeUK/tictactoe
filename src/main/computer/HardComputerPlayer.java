package computer;

import tictactoe.Board;
import tictactoe.Player;
import tictactoe.PlayerResponse;

public class HardComputerPlayer implements Player {

  private String name;
  private int thisPlayer;
  private int otherPlayer;

  public HardComputerPlayer(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public PlayerResponse playTurn(Board board) {
    thisPlayer = board.getCurrentPlayer();
    otherPlayer = board.getNextPlayer();
    board.placeToken(minimax(board).bestPosition);
    return PlayerResponse.TURN_COMPLETE;
  }

  private MinimaxResult minimax(Board board) {
    int bestScore = (board.getCurrentPlayer() == thisPlayer) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
    int bestPosition = -1;
    MinimaxResult bestResult = new MinimaxResult(bestScore, bestPosition);

    if (board.gameIsWon() || board.gameIsTied())
      bestResult.bestScore = computeScore(board);
    else
      computeBestNextTurn(board, bestResult);

    return bestResult;
  }

  private int computeScore(Board board) {
    int lastPlayer = board.getNextPlayer();
    if (board.gameIsWon() && lastPlayer == thisPlayer)
      return 100;
    else if (board.gameIsWon() && lastPlayer == otherPlayer)
      return -100;
    else
      return 0;
  }

  private void computeBestNextTurn(Board board, MinimaxResult bestResult) {
    for (int ap : board.getAvailablePositions()) {
      board.placeToken(ap);
      if (board.getCurrentPlayer() == thisPlayer)
        maximise(ap, board, bestResult);
      else
        minimise(ap, board, bestResult);
      board.getPositions()[ap] = -1;
    }
  }

  private void maximise(int ap, Board board, MinimaxResult bestResult) {
    swapPlayerOrder(board);
    int currentScore = minimax(board).bestScore;
    swapPlayerOrder(board);
    if (currentScore > bestResult.bestScore) {
      bestResult.bestScore = currentScore;
      bestResult.bestPosition = ap;
    }
  }

  private void minimise(int ap, Board board, MinimaxResult bestResult) {
    swapPlayerOrder(board);
    int currentScore = minimax(board).bestScore;
    swapPlayerOrder(board);
    if (currentScore < bestResult.bestScore) {
      bestResult.bestScore = currentScore;
      bestResult.bestPosition = ap;
    }
  }

  private void swapPlayerOrder(Board board) {
    int temp = board.getCurrentPlayer();
    board.setCurrentPlayer(board.getNextPlayer());
    board.setNextPlayer(temp);
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
