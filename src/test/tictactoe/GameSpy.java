package tictactoe;

public class GameSpy extends Game {

  public boolean receiveUserInputWasCalled;
  private String stubType;

  public GameSpy(String stubType) {
    super(null, null);
    this.stubType = stubType;
  }

  @Override
  public boolean play(TurnPresenter presenter) {
    TurnResponseModel responseModel = new TurnResponseModel();
    responseModel.turnResult = stubType.equals("computer") ? "turn_complete" : "user_input_required";
    presenter.present(responseModel);
    return true;
  }

  @Override
  public void receiveUserInput(String input) {
    receiveUserInputWasCalled = true;
  }
}
