package ui.console;

import java.util.Arrays;
import java.util.List;
import ui.ViewModel;

public class MainMenuViewModel implements ViewModel {

  String title = "TicTacToe";
  List<String> menuOptions = Arrays.asList("Play a game.", "Exit");

  @Override
  public char[] getInputOptions() {
    return "12".toCharArray();
  }
}
