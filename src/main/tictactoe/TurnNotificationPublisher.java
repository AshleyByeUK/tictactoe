package tictactoe;

import ui.console.gamePlay.GamePlayViewModel;

public interface TurnNotificationPublisher {

  void subscribe(TurnNotificationSubscriber subscriber);

  void notify(TurnNotification responseModel);

  GamePlayViewModel getViewModel();
}
