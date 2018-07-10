package computer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import tictactoe.Board;
import tictactoe.Player;
import tictactoe.UserInterface;

public class ComputerPlayer implements Player {

  private final String name;
  private final Random random;

  public ComputerPlayer(String name) {
    this(name, ThreadLocalRandom.current());
  }

  public ComputerPlayer(String name, Random random) {
    this.name = name;
    this.random = random;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void playTurn(Board board, UserInterface ui) {
    board.placeToken(computeBestMove(board));
  }

  private int computeBestMove(Board board) {
    List<Integer> availableSpaces = board.getAvailablePositions();

    for (int as : availableSpaces)
      if (isBestPosition(as))
        return as;
      else if (isGameEndingPosition(as, board, board.getCurrentPlayer()))
        return as;
      else if (isGameEndingPosition(as, board, board.getNextPlayer()))
        return as;
      else
        revertBoard(as, board);

    return availableSpaces.get(random.nextInt(availableSpaces.size()));
  }

  private boolean isBestPosition(int as) {
    return as == 4;
  }

  private boolean isGameEndingPosition(int spot, Board board, int player) {
    board.getPositions()[spot] = player;
    if (board.gameIsOver())
      return revertBoard(spot, board);
    return false;
  }

  private boolean revertBoard(int spot, Board board) {
    board.getPositions()[spot] = -1;
    return true;
  }
}
