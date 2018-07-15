package tictactoe.game;

import tictactoe.TurnPresenter;
import tictactoe.TurnResponseModel;
import tictactoe.TurnResponseObserver;
import ui.console.gamePlay.GamePlayViewModel;

public class TicTacToeTurnPresenter implements TurnPresenter {

  private TurnResponseObserver ui;
  private GamePlayViewModel viewModel = new GamePlayViewModel();

  @Override
  public void register(TurnResponseObserver ui) {
    this.ui = ui;
  }

  @Override
  public void present(TurnResponseModel responseModel) {
    viewModel = populateViewModel((TicTacToeTurnResponseModel) responseModel);
    ui.notifyTurnPlayed(this);
  }

  private GamePlayViewModel populateViewModel(TicTacToeTurnResponseModel responseModel) {
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

  @Override
  public GamePlayViewModel getViewModel() {
    return viewModel;
  }
}
