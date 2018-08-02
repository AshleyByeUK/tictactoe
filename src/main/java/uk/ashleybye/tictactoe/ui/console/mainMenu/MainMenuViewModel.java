package uk.ashleybye.tictactoe.ui.console.mainMenu;

import java.util.Arrays;
import java.util.List;
import uk.ashleybye.tictactoe.ui.ViewModel;


public class MainMenuViewModel implements ViewModel {

  String title = "TicTacToeGame";
  List<String> menuOptions = Arrays.asList("Play a game.", "Exit.");
}
