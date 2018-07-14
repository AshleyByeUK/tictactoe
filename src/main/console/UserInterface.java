package console;

import tictactoe.TurnPresenter;

public interface UserInterface {

  void setGamePlayView(GamePlayView gamePlayView);

  void notifyTurnPlayed(TurnPresenter presenter);

  void launch();
}
