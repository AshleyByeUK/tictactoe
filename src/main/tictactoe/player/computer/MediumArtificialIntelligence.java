package tictactoe.player.computer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import tictactoe.Board;

public class MediumArtificialIntelligence implements ArtificialIntelligence {

  private final Random random;

  public MediumArtificialIntelligence() {
    this(ThreadLocalRandom.current());
  }

  MediumArtificialIntelligence(Random random) {
    this.random = random;
  }

  @Override
  public int computeBestMove(Board board) {
    List<Integer> availablePositions = board.getAvailablePositions();

    for (int ap : availablePositions)
      if (isBestPosition(ap))
        return ap;
      else if (isGameEndingPosition(ap, board, board.getCurrentPlayer()))
        return ap;
      else if (isGameEndingPosition(ap, board, board.getNextPlayer()))
        return ap;
      else
        revertBoard(ap, board);

    return availablePositions.get(random.nextInt(availablePositions.size()));
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
}
