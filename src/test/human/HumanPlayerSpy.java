package human;

import tictactoe.Board;
import tictactoe.TurnResult;
import tictactoe.UserInterface;

public class HumanPlayerSpy extends HumanPlayer {

  public boolean playedMove;

  public HumanPlayerSpy(String name) {
    super(name);
  }

  @Override
  public TurnResult playTurn(Board board, UserInterface ui) {
    playedMove = true;
    ui.getInputForPlayer(name, 8);
    return null;
  }
}
