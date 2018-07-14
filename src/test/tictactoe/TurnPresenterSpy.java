package tictactoe;

import console.GamePlayViewModel;

public class TurnPresenterSpy extends TurnPresenter {

  public boolean getViewModelWasCalled;

  @Override
  public GamePlayViewModel getViewModel() {
    GamePlayViewModel viewModel = super.getViewModel();
    viewModel.userInputIsRequired = true;
    getViewModelWasCalled = true;
    return viewModel;
  }
}
