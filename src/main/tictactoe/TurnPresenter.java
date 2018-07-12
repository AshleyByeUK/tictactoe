package tictactoe;

import console.GamePlayController;
import console.GamePlayViewModel;

public class TurnPresenter {

  private GamePlayController controller;
  private GamePlayViewModel viewModel;

  public void register(GamePlayController controller) {
    this.controller = controller;
  }

  public void present(TurnResponseModel responseModel) {
    viewModel = new GamePlayViewModel();
    viewModel.gameState = responseModel.gameState;
    controller.viewModelUpdated(this);
  }

  public GamePlayViewModel getViewModel() {
    return viewModel;
  }
}
