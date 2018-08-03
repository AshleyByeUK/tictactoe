package uk.ashleybye.tictactoe.ui.console;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.tictactoe.PlayerFactory;
import uk.ashleybye.tictactoe.tictactoe.TurnNotificationPublisher;
import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeTurnNotification;
import uk.ashleybye.tictactoe.ui.UserInterface;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolView;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerView;

public class UserInterfaceMock implements UserInterface {

  private List<Integer> positionsToPlay;
  private int nextPositionToPlay = 0;

    @Override
  public void setPlayerFactory(PlayerFactory playerFactory) {

  }

  @Override
  public void setMainMenuView(MainMenuView view) {

  }

  @Override
  public void setGamePlayView(GamePlayView view) {

  }

  @Override
  public void setSelectPlayerView(SelectPlayerView view) {

  }

  @Override
  public void setSelectFirstPlayerView(SelectFirstPlayerView view) {

  }

  @Override
  public void setChangePlayersSymbolsView(ChangePlayersSymbolsView view) {

  }

  @Override
  public void setSelectPlayerSymbolView(SelectPlayerSymbolView view) {

  }

  @Override
  public boolean launch() {
    return false;
  }

  public void setPlayerPositionsToPlay(Integer... positions) {
    positionsToPlay = Arrays.asList(positions);
  }

  @Override
  public int getPositionToPlay(TicTacToeTurnNotification turnNotification) {
    return positionsToPlay.get(nextPositionToPlay++) - 1;
  }

  @Override
  public void receiveTurnPlayedNotification(TurnNotificationPublisher publisher) {

  }
}
