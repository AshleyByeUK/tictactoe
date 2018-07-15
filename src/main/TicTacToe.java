import java.util.Scanner;
import tictactoe.player.TicTacToePlayerFactory;
import ui.UserInterface;
import ui.console.ConsoleUserInterface;
import ui.console.firstPlayer.SelectFirstPlayerView;
import ui.console.gamePlay.GamePlayView;
import ui.console.mainMenu.MainMenuView;
import ui.console.playerSymbol.ChangePlayersSymbolsView;
import ui.console.playerSymbol.SelectPlayerSymbolView;
import ui.console.playerType.SelectPlayerView;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    UserInterface ui = new ConsoleUserInterface(input);
    ui.setPlayerFactory(new TicTacToePlayerFactory());
    ui.setMainMenuView(new MainMenuView());
    ui.setSelectPlayerView(new SelectPlayerView());
    ui.setSelectFirstPlayerView(new SelectFirstPlayerView());
    ui.setChangePlayersSymbolsView(new ChangePlayersSymbolsView());
    ui.setSelectPlayerSymbolView(new SelectPlayerSymbolView());
    ui.setGamePlayView(new GamePlayView());
    ui.launch();
  }
}
