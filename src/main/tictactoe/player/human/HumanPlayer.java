package tictactoe.player.human;

import static tictactoe.player.TurnResult.INPUT_REQUIRED;
import static tictactoe.player.TurnResult.POSITION_TAKEN;
import static tictactoe.player.TurnResult.TURN_COMPLETE;

import tictactoe.Board;
import tictactoe.ControllablePlayer;
import tictactoe.Player;
import tictactoe.player.TurnResult;

public class HumanPlayer implements Player, ControllablePlayer {

  private final String name;
  private int positionToPlay;

  public HumanPlayer(String name) {
    this.name = name;
    positionToPlay = -1;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public TurnResult playTurn(Board board) {
    if (positionToPlay == -1)
      return INPUT_REQUIRED;
    else if (!board.positionIsAvailable(positionToPlay))
      return POSITION_TAKEN;
    else {
      return takeTurn(board);
    }
  }

  private TurnResult takeTurn(Board board) {
    board.placeSymbolAtPosition(positionToPlay);
    positionToPlay = -1;
    return TURN_COMPLETE;
  }

  @Override
  public void receiveInput(int value) {
    positionToPlay = value;
  }
}
