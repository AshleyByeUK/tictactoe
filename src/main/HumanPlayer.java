public class HumanPlayer implements Player {

  protected final String name;
  private final String token;

  public HumanPlayer(String token, String name) {
    this.token = token;
    this.name = name;
  }

  @Override
  public String getToken() {
    return token;
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
      spot = game.getUserInterface().getInputForPlayer(name, spotsAvailable);
    while (!validInput(spot, spotsAvailable, game));
    return Integer.parseInt(spot);
  }

  private boolean validInput(String spot, int spotsAvailable, Game game) {
    if (isValidInput(spot, spotsAvailable))
      if (game.isAvailablePosition(Integer.parseInt(spot)))
        return true;
      else
        game.getUserInterface().sendMessage("Position is taken");
    else
      game.getUserInterface().sendMessage("Invalid input");
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
