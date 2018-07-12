package human;

import static tictactoe.PlayerResponse.INPUT_REQUIRED;
import static tictactoe.PlayerResponse.INVALID_INPUT;
import static tictactoe.PlayerResponse.TURN_COMPLETE;

import tictactoe.Board;
import tictactoe.Player;
import tictactoe.PlayerResponse;

public class HumanPlayer implements Player {

  private final String name;
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
  public PlayerResponse playTurn(Board board) {
    if (positionToPlay == null)
      return INPUT_REQUIRED;
    else if (!validInput(positionToPlay, board))
      return INVALID_INPUT;
    else {
      return takeTurn(board);
    }
  }

  private PlayerResponse takeTurn(Board board) {
    board.placeToken(Integer.parseInt(positionToPlay));
    positionToPlay = null;
    return TURN_COMPLETE;
  }

  private boolean validInput(String spot, Board board) {
    if (isValidInput(spot, board.getPositions().length - 1))
      if (board.positionIsAvailable(Integer.parseInt(spot)))
        return true;
    return false;
  }

  private boolean isValidInput(String input, int spotsAvailable) {
    boolean valid = false;
    try {
      int spot = Integer.parseInt(input);
      valid = (spot >= 0 && spot <= spotsAvailable);
    } catch (NumberFormatException e) {
      // Intentionally empty.
    }
    return valid;
  }

  public void receiveInput(String value) {
    positionToPlay = value;
  }
}
