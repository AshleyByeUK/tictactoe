import java.util.Scanner;
import ui.UserInterface;
import ui.console.ChangePlayersSymbolsView;
import ui.console.ConsoleUserInterface;
import ui.console.GamePlayView;
import ui.console.MainMenuView;
import ui.console.SelectFirstPlayerView;
import ui.console.SelectPlayerSymbolView;
import ui.console.SelectPlayerView;

public class TicTacToe {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    UserInterface ui = new ConsoleUserInterface(input);
    ui.setMainMenuView(new MainMenuView());
    ui.setSelectPlayerView(new SelectPlayerView());
    ui.setSelectFirstPlayerView(new SelectFirstPlayerView());
    ui.setChangePlayersSymbolsView(new ChangePlayersSymbolsView());
    ui.setSelectPlayerSymbolView(new SelectPlayerSymbolView());
    ui.setGamePlayView(new GamePlayView());
    ui.launch();
  }
}
