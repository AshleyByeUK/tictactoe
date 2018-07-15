package ui;

import tictactoe.TurnPresenter;

public interface UserInterface {

  void setGamePlayView(View gamePlayView);

  void notifyTurnPlayed(TurnPresenter presenter);

  void launch();
}
