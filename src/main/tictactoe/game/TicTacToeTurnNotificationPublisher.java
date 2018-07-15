package tictactoe.game;

import tictactoe.TurnNotification;
import tictactoe.TurnNotificationPublisher;
import tictactoe.TurnNotificationSubscriber;
import ui.console.gamePlay.GamePlayViewModel;

public class TicTacToeTurnNotificationPublisher implements TurnNotificationPublisher {

  private TurnNotificationSubscriber subscriber;
  private GamePlayViewModel viewModel = new GamePlayViewModel();

  @Override
  public void subscribe(TurnNotificationSubscriber subscriber) {
    this.subscriber = subscriber;
  }

  @Override
  public void notify(TurnNotification notification) {
    viewModel = populateViewModel((TicTacToeTurnNotification) notification);
    subscriber.receiveTurnPlayedNotification(this);
  }

  private GamePlayViewModel populateViewModel(TicTacToeTurnNotification notification) {
    viewModel.gameState = notification.gameState;
    viewModel.turnResult = notification.turnResult;
    viewModel.currentPlayerName = notification.currentPlayerName;
    viewModel.board = notification.board;
    viewModel.gameResult = notification.gameResult;
    viewModel.lastPositionPlayed = notification.lastPositionPlayed;
    viewModel.availablePositions = notification.availablePositions;
    viewModel.userInputIsRequired = !notification.turnResult.equals("turn_complete");
    viewModel.userPositionIsTaken = notification.turnResult.equals("position_taken");
    return viewModel;
  }

  @Override
  public GamePlayViewModel getViewModel() {
    return viewModel;
  }
}
