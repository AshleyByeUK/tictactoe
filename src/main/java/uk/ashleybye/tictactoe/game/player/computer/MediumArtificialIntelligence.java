package uk.ashleybye.tictactoe.game.player.computer;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import uk.ashleybye.tictactoe.game.Board;
import uk.ashleybye.tictactoe.game.GameState;


public class MediumArtificialIntelligence implements ArtificialIntelligence {

  private final Random random;

  public MediumArtificialIntelligence() {
    this(ThreadLocalRandom.current());
  }

  MediumArtificialIntelligence(Random random) {
    this.random = random;
  }

  @Override
  public int computeNextMove(GameState gameState) {
    Board board = gameState.getBoard();
    for (int ap : board.getAvailablePositions())
      if (isBestPosition(ap))
        return ap;
      else if (isGameEndingPosition(ap, board, gameState.getCurrentPlayer()))
        return ap;
      else if (isGameEndingPosition(ap, board, gameState.getNextPlayer()))
        return ap;
      else
        revertBoard(ap, board);

    return chooseRandomPosition(board);
  }

  private boolean isBestPosition(int pos) {
    return pos == 4;
  }

  private boolean isGameEndingPosition(int pos, Board board, int player) {
    board.getPositions()[pos] = player;
    if (!board.gameIsWon())
      return false;
    revertBoard(pos, board);
    return true;
  }

  private void revertBoard(int pos, Board board) {
    board.getPositions()[pos] = -1;
  }

  private Integer chooseRandomPosition(Board board) {
    return board.getAvailablePositions().get(random.nextInt(board.getAvailablePositions().size()));
  }
}
