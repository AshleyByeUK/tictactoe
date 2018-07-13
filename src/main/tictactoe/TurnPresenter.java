package tictactoe;

import console.GamePlayViewController;
import console.GamePlayViewModel;

public class TurnPresenter {

  private GamePlayViewController controller;
  private GamePlayViewModel viewModel;

  public void register(GamePlayViewController controller) {
    this.controller = controller;
  }

  public void present(TurnResponseModel responseModel) {
    viewModel = populateViewModel(responseModel);
    controller.viewModelUpdated(this);
  }

  private GamePlayViewModel populateViewModel(TurnResponseModel responseModel) {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    viewModel.gameState = responseModel.gameState;
    viewModel.turnResult = responseModel.turnResult;
    viewModel.currentPlayerName = responseModel.currentPlayerName;
    viewModel.board = responseModel.board;
    viewModel.gameResult = responseModel.gameResult;
    viewModel.lastPositionPlayed = responseModel.lastPositionPlayed;
    viewModel.availablePositions = responseModel.availablePositions;
    viewModel.userInputIsRequired = !responseModel.turnResult.equals("turn_complete");
    viewModel.userPositionIsTaken = responseModel.turnResult.equals("position_taken");
    return viewModel;
  }

  public GamePlayViewModel getViewModel() {
    return viewModel;
  }
}
