package tictactoe.game;

import ui.console.gamePlay.GamePlayViewModel;

public class TicTacToeTurnNotificationPublisherSpy extends TicTacToeTurnNotificationPublisher {

  public boolean getViewModelWasCalled;

  @Override
  public GamePlayViewModel getViewModel() {
    GamePlayViewModel viewModel = super.getViewModel();
    viewModel.userInputIsRequired = true;
    getViewModelWasCalled = true;
    return viewModel;
  }
}
