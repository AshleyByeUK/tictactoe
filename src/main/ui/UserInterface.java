package ui;

import tictactoe.TurnPresenter;
import ui.console.ChangePlayersSymbolsView;
import ui.console.GamePlayView;
import ui.console.MainMenuView;
import ui.console.SelectFirstPlayerView;
import ui.console.SelectPlayerSymbolView;
import ui.console.SelectPlayerView;

public interface UserInterface {

  void setMainMenuView(MainMenuView view);

  void setGamePlayView(GamePlayView view);

  void setSelectPlayerView(SelectPlayerView view);

  void setSelectFirstPlayerView(SelectFirstPlayerView view);

  void setChangePlayersSymbolsView(ChangePlayersSymbolsView view);

  void setSelectPlayerSymbolView(SelectPlayerSymbolView view);

  void launch();

  void notifyTurnPlayed(TurnPresenter presenter);
}
