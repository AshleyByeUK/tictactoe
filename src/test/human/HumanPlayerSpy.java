package human;

import tictactoe.Board;
import tictactoe.UserInterface;

public class HumanPlayerSpy extends HumanPlayer {

  public boolean playedMove;

  public HumanPlayerSpy(String name) {
    super(name);
  }

  @Override
  public void playTurn(Board board, UserInterface ui) {
    playedMove = true;
    ui.getInputForPlayer(name, 8);
  }
}
