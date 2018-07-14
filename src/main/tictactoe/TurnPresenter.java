package tictactoe;

import console.GamePlayViewModel;
import console.UserInterface;

public class TurnPresenter {

  private UserInterface ui;
  private GamePlayViewModel viewModel = new GamePlayViewModel();

  public void register(UserInterface ui) {
    this.ui = ui;
  }

  public void present(TurnResponseModel responseModel) {
    viewModel = populateViewModel(responseModel);
    ui.notifyTurnPlayed(this);
  }

  private GamePlayViewModel populateViewModel(TurnResponseModel responseModel) {
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
