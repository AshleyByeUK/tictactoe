package tictactoe;

import ui.console.gamePlay.GamePlayViewModel;

public interface TurnPresenter {

  void register(TurnResponseObserver ui);

  void present(TurnResponseModel responseModel);

  GamePlayViewModel getViewModel();
}
