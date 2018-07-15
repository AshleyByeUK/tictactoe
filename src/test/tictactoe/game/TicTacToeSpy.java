package tictactoe.game;

import tictactoe.TurnPresenter;

public class TicTacToeSpy extends TicTacToe {

  public boolean receiveUserInputWasCalled;
  public int userInput;
  private String stubType;

  public TicTacToeSpy(String stubType) {
    super(null, null);
    this.stubType = stubType;
  }

  @Override
  public boolean play(TurnPresenter presenter) {
    TicTacToeTurnResponseModel responseModel = new TicTacToeTurnResponseModel();
    responseModel.turnResult = stubType.equals("computer") ? "turn_complete" : "user_input_required";
    presenter.present(responseModel);
    return true;
  }

  @Override
  public void receiveUserInput(int input) {
    receiveUserInputWasCalled = true;
    userInput = input;
  }
}
