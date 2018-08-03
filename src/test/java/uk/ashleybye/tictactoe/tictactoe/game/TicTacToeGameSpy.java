package uk.ashleybye.tictactoe.tictactoe.game;

public class TicTacToeGameSpy extends TicTacToeGame {

  private String stubType;

  public TicTacToeGameSpy(String stubType) {
    super(null, null);
    this.stubType = stubType;
  }

  @Override
  public boolean play(TicTacToeTurnNotificationPublisher publisher) {
    TicTacToeTurnNotification notification = new TicTacToeTurnNotification();
    notification.userInputIsRequired = !stubType.equals("computer");
    publisher.notify(notification);
    return true;
  }
}
