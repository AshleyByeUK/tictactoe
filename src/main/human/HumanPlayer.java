package human;

import tictactoe.Board;
import tictactoe.Player;
import tictactoe.TurnResult;
import tictactoe.UserInterface;

public class HumanPlayer implements Player {

  protected final String name;
  private String positionToPlay;

  public HumanPlayer(String name) {
    this.name = name;
    positionToPlay = null;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public TurnResult playTurn(Board board, UserInterface ui) {
    if (positionToPlay == null)
      return TurnResult.INPUT_REQUIRED;
    else if (!validInput(positionToPlay, board, ui))
      return TurnResult.INVALID_INPUT;

//    String spot;
//    do
//      spot = getInputForPlayer(board, ui);
//    while (!validInput(spot, board, ui));
    board.placeToken(Integer.parseInt(positionToPlay));
    positionToPlay = null;

    return TurnResult.TURN_COMPLETE;
  }

  private String getInputForPlayer(Board board, UserInterface ui) {
    ui.showAvailablePositions(board);
    return ui.getInputForPlayer(name, board.getPositions().length - 1);
  }

  boolean validInput(String spot, Board board, UserInterface ui) {
    if (isValidInput(spot, board.getPositions().length - 1))
      if (board.positionIsAvailable(Integer.parseInt(spot))) {
        System.out.println("valid? " + true);
        return true;
      }
      else
        ui.showMessage("Position is taken");
    else
      ui.showMessage("Invalid input");
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

  public void receiveInput(String value) {
    positionToPlay = value;
  }
}
