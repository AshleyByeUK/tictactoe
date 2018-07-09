public class HumanPlayerSpy extends HumanPlayer {

  public boolean playedMove;
  public int move;

  public HumanPlayerSpy(String token, String name) {
    super(token, name);
  }

  @Override
  public int playTurn(Game game) {
    playedMove = true;
    game.getUserInterface().getPlayersMove(name);
    return move++;
  }
}
