package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.Player;
import uk.ashleybye.tictactoe.game.PlayerFactory;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerView;
import uk.ashleybye.tictactoe.ui.console.firstPlayer.SelectFirstPlayerViewModel;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.ChangePlayersSymbolsViewModel;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolView;
import uk.ashleybye.tictactoe.ui.console.playerSymbol.SelectPlayerSymbolViewModel;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerView;
import uk.ashleybye.tictactoe.ui.console.playerType.SelectPlayerViewModel;

public class ConsoleGameConfigurator {

  private final Scanner input;
  private final PlayerFactory playerFactory;
  final String YES = "Y";
  final String PLAYER_ONE_NAME = "Player 1";
  final String PLAYER_TWO_NAME = "Player 2";
  final String PLAYER_ONE_SYMBOL = "X";
  final String PLAYER_TWO_SYMBOL = "O";
  final String FIRST_PLAYER = "first";
  final String SECOND_PLAYER = "second";

  public ConsoleGameConfigurator(Scanner input, PlayerFactory playerFactory) {
    this.input = input;
    this.playerFactory = playerFactory;
  }

  GameOptions configureGame() {
    GameOptions options = new GameOptions();
    options.setPlayerOneName(PLAYER_ONE_NAME);
    options.setPlayerTwoName(PLAYER_TWO_NAME);
    options.setPlayerOneSymbol(PLAYER_ONE_SYMBOL);
    options.setPlayerTwoSymbol(PLAYER_TWO_SYMBOL);
    options.setPlayerOneType(launchSelectPlayerMenu(FIRST_PLAYER));
    options.setPlayerTwoType(launchSelectPlayerMenu(SECOND_PLAYER));
    options.setFirstPlayer(launchSelectFirstPlayerMenu());
    boolean changeSymbols = launchChangePlayersSymbolsMenu();

    if (changeSymbols) {
      boolean changed = false;
      while (!changed) {
        options.setPlayerOneSymbol(launchSelectPlayerSymbolMenu(PLAYER_ONE_NAME));
        options.setPlayerTwoSymbol(launchSelectPlayerSymbolMenu(PLAYER_TWO_NAME));

        if (!symbolsDiffer(options))
          showPlayerSymbolsMustDifferMessage();
        else
          changed = true;
      }
    }

    return options;
  }

  boolean symbolsDiffer(GameOptions options) {
    return !options.getPlayerOneSymbol().equalsIgnoreCase(options.getPlayerTwoSymbol());
  }

  private String launchSelectPlayerMenu(String position) {
    SelectPlayerViewModel viewModel = new SelectPlayerViewModel();
    SelectPlayerView view = new SelectPlayerView();
    viewModel.playerTypes = playerFactory.listPlayerTypes();
    viewModel.position = position;
    ViewController<SelectPlayerView, SelectPlayerViewModel> controller = new ViewController<SelectPlayerView, SelectPlayerViewModel>(
        viewModel, view);
    controller.updateView();
    int choice = Integer.parseInt(controller.getUserInput(input));
    return playerFactory.listPlayerTypes().get(choice - 1);
  }

  private int launchSelectFirstPlayerMenu() {
    SelectFirstPlayerViewModel viewModel = new SelectFirstPlayerViewModel();
    SelectFirstPlayerView view = new SelectFirstPlayerView();
    ViewController<SelectFirstPlayerView, SelectFirstPlayerViewModel> controller = new ViewController<SelectFirstPlayerView, SelectFirstPlayerViewModel>(
        viewModel, view);
    controller.updateView();
    return controller.getUserInput(input).equals(YES) ? 1 : 0;
  }

  private boolean launchChangePlayersSymbolsMenu() {
    ChangePlayersSymbolsViewModel viewModel = new ChangePlayersSymbolsViewModel();
    ChangePlayersSymbolsView view = new ChangePlayersSymbolsView();
    viewModel.playerOneSymbol = PLAYER_ONE_SYMBOL;
    viewModel.playerTwoSymbol = PLAYER_TWO_SYMBOL;
    ViewController<ChangePlayersSymbolsView, ChangePlayersSymbolsViewModel> controller = new ViewController<ChangePlayersSymbolsView, ChangePlayersSymbolsViewModel>(
        viewModel, view);
    controller.updateView();
    return controller.getUserInput(input).equals(YES);
  }

  private String launchSelectPlayerSymbolMenu(String name) {
    SelectPlayerSymbolViewModel viewModel = new SelectPlayerSymbolViewModel();
    SelectPlayerSymbolView view = new SelectPlayerSymbolView();
    viewModel.playerName = name;
    ViewController<SelectPlayerSymbolView, SelectPlayerSymbolViewModel> controller = new ViewController<SelectPlayerSymbolView, SelectPlayerSymbolViewModel>(
        viewModel, view);
    controller.updateView();
    return controller.getUserInput(input);
  }

  private void showPlayerSymbolsMustDifferMessage() {
    System.out.println("Player symbols cannot be the same for both players.");
  }
}
