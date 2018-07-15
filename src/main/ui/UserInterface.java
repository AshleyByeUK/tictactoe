package ui;

import tictactoe.TurnResponseObserver;
import tictactoe.PlayerFactory;
import ui.console.firstPlayer.SelectFirstPlayerView;
import ui.console.gamePlay.GamePlayView;
import ui.console.mainMenu.MainMenuView;
import ui.console.playerSymbol.ChangePlayersSymbolsView;
import ui.console.playerSymbol.SelectPlayerSymbolView;
import ui.console.playerType.SelectPlayerView;

public interface UserInterface extends TurnResponseObserver {

  void setPlayerFactory(PlayerFactory playerFactory);

  void setMainMenuView(MainMenuView view);

  void setGamePlayView(GamePlayView view);

  void setSelectPlayerView(SelectPlayerView view);

  void setSelectFirstPlayerView(SelectFirstPlayerView view);

  void setChangePlayersSymbolsView(ChangePlayersSymbolsView view);

  void setSelectPlayerSymbolView(SelectPlayerSymbolView view);

  boolean launch();
}
