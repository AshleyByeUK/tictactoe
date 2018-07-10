package computer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import tictactoe.Game;
import tictactoe.Player;

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
  public int playTurn(Game game) {
    return computeBestMove(game);
  }

  public int computeBestMove(Game game) {
    List<Integer> availableSpaces = computeAvailablePositions(game);

    for (int as : availableSpaces)
      if (isBestPosition(as))
        return as;
      else if (isGameEndingPosition(as, game, game.getCurrentPlayer()))
        return as;
      else if (isGameEndingPosition(as, game, game.getNextPlayer()))
        return as;
      else
        revertBoard(as, game);

    return availableSpaces.get(random.nextInt(availableSpaces.size()));
  }

  private List<Integer> computeAvailablePositions(Game game) {
    List<Integer> availableSpaces = new ArrayList<>();
    for (int i = 0; i < game.getBoard().length; i++)
      if (game.getBoard()[i] == -1)
        availableSpaces.add(i);
    return availableSpaces;
  }

  private boolean isBestPosition(int as) {
    return as == 4;
  }

  private boolean isGameEndingPosition(int spot, Game game, int player) {
    game.getBoard()[spot] = player;
    if (game.gameIsOver())
      return revertBoard(spot, game);
    return false;
  }

  private boolean revertBoard(int spot, Game game) {
    game.getBoard()[spot] = -1;
    return true;
  }
}
