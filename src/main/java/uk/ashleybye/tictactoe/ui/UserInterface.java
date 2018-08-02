package uk.ashleybye.tictactoe.ui;


import uk.ashleybye.tictactoe.tictactoe.PlayerFactory;
import uk.ashleybye.tictactoe.tictactoe.TurnNotificationSubscriber;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolView;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerView;

public interface UserInterface extends TurnNotificationSubscriber {

  void setPlayerFactory(PlayerFactory playerFactory);

  void setMainMenuView(MainMenuView view);

  void setGamePlayView(GamePlayView view);

  void setSelectPlayerView(SelectPlayerView view);

  void setSelectFirstPlayerView(SelectFirstPlayerView view);

  void setChangePlayersSymbolsView(ChangePlayersSymbolsView view);

  void setSelectPlayerSymbolView(SelectPlayerSymbolView view);

  boolean launch();
}
