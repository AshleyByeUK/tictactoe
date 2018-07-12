package computer;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import tictactoe.Board;
import tictactoe.Player;
import tictactoe.PlayerResponse;

public class MediumComputerPlayer implements Player {

  private final String name;
  private final Random random;

  public MediumComputerPlayer(String name) {
    this(name, ThreadLocalRandom.current());
  }

  public MediumComputerPlayer(String name, Random random) {
    this.name = name;
    this.random = random;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public PlayerResponse playTurn(Board board) {
    board.placeToken(computeBestMove(board));
    return PlayerResponse.TURN_COMPLETE;
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
    if (board.gameIsWon())
      return revertBoard(spot, board);
    return false;
  }

  private boolean revertBoard(int spot, Board board) {
    board.getPositions()[spot] = -1;
    return true;
  }
}