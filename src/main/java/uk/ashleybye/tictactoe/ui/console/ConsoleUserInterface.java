package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.GamePlayBoundary;
import uk.ashleybye.tictactoe.game.PlayerFactory;
import uk.ashleybye.tictactoe.ui.UserInterface;

public class ConsoleUserInterface implements UserInterface {

  private Scanner input;
  private PlayerFactory playerFactory;

  public ConsoleUserInterface(Scanner input, PlayerFactory playerFactory) {
    this.playerFactory = playerFactory;
    this.input = input;
  }

  @Override
  public boolean launch() {
    if (selectMainMenuOption() == 0)
      return launchGame();
    else
      return false;
  }

  private int selectMainMenuOption() {
    ConsoleMainMenu mainMenu = new ConsoleMainMenu(input);
    return mainMenu.selectOption();
  }

  private boolean launchGame() {
    ConsoleGameConfigurator gameConfigurator = new ConsoleGameConfigurator(input, playerFactory);
    GameOptions options = gameConfigurator.configureGame();
    GamePlayBoundary boundary = new ConsoleGamePlayBoundary(input);
    Game game = Game.playTicTacToe(options, boundary, playerFactory);
    return game.play();
  }
}
