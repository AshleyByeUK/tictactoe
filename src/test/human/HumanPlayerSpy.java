package human;

import tictactoe.Game;

public class HumanPlayerSpy extends HumanPlayer {

  public boolean playedMove;
  public int move;

  public HumanPlayerSpy(String name) {
    super(name);
  }

  @Override
  public int playTurn(Game game) {
    playedMove = true;
    game.getUserInterface().getInputForPlayer(name, 8);
    return move++;
  }
}
