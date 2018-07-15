package ui.console.mainMenu;

import java.util.Arrays;
import java.util.List;
import ui.ViewModel;

public class MainMenuViewModel implements ViewModel {

  String title = "TicTacToeGame";
  List<String> menuOptions = Arrays.asList("Play a game.", "Exit");

  @Override
  public char[] getInputOptions() {
    return "12".toCharArray();
  }
}
