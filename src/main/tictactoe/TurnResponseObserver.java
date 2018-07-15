package tictactoe;

public interface TurnResponseObserver {

  void notifyTurnPlayed(TurnPresenter presenter);
}
