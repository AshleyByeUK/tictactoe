package tictactoe;

public interface TurnResponseObserver {

  void receiveTurnPlayedNotification(TurnPresenter presenter);
}
