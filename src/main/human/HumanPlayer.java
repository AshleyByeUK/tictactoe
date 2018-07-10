package human;

import tictacttoe.Game;
import tictacttoe.Player;

public class HumanPlayer implements Player {

  protected final String name;

  public HumanPlayer(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int playTurn(Game game) {
    String spot;
    int spotsAvailable = game.getBoard().length - 1;
    do
      spot = getInputForPlayer(game, spotsAvailable);
    while (!validInput(spot, spotsAvailable, game));
    return Integer.parseInt(spot);
  }

  private String getInputForPlayer(Game game, int spotsAvailable) {
    game.getUserInterface().showAvailablePositions(game.getBoard());
    return game.getUserInterface().getInputForPlayer(name, spotsAvailable);
  }

  private boolean validInput(String spot, int spotsAvailable, Game game) {
    if (isValidInput(spot, spotsAvailable))
      if (game.positionIsAvailable(Integer.parseInt(spot)))
        return true;
      else
        game.getUserInterface().showMessage("Position is taken");
    else
      game.getUserInterface().showMessage("Invalid input");
    return false;
  }

  private boolean isValidInput(String input, int spotsAvailable) {
    boolean valid = false;
    try {
      int spot = Integer.parseInt(input);
      if (spot >= 0 && spot <= spotsAvailable)
        valid = true;
    } catch (NumberFormatException e) {
      // Intentionally empty.
    }
    return valid;
  }
}
