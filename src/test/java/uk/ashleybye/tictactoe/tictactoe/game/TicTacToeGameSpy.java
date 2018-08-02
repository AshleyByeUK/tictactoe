package uk.ashleybye.tictactoe.tictactoe.game;

public class TicTacToeGameSpy extends TicTacToeGame {

  public boolean receiveUserInputWasCalled;
  public int userInput;
  private String stubType;

  public TicTacToeGameSpy(String stubType) {
    super(null, null);
    this.stubType = stubType;
  }

  @Override
  public boolean play(TicTacToeTurnNotificationPublisher publisher) {
    TicTacToeTurnNotification notification = new TicTacToeTurnNotification();
    notification.turnResult = stubType.equals("computer") ? "turn_complete" : "user_input_required";
    publisher.notify(notification);
    return true;
  }

  @Override
  public void receiveUserInput(int input) {
    receiveUserInputWasCalled = true;
    userInput = input;
  }
}
