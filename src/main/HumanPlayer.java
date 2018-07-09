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
    boolean validInput = false;  // for input validation
    int spot;
    do {
      spot = game.getUserInterface().getPlayersMove(name);
      if (game.isValidPosition(spot)) {
        validInput = true;  // input okay, exit loop
      }
    } while (!validInput);  // repeat until input is valid
    return spot;
  }
}
