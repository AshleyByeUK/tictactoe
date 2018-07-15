package ui.console;


import tictactoe.game.TicTacToe;
import tictactoe.game.TicTacToeTurnPresenter;
import java.util.Arrays;
import java.util.Scanner;
import tictactoe.player.computer.ComputerPlayer;
import tictactoe.player.computer.HardArtificialIntelligence;
import tictactoe.player.computer.MediumArtificialIntelligence;
import tictactoe.player.human.HumanPlayer;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.TurnPresenter;
import ui.UserInterface;
import ui.console.firstPlayer.SelectFirstPlayerView;
import ui.console.firstPlayer.SelectFirstPlayerViewModel;
import ui.console.gamePlay.GamePlayView;
import ui.console.gamePlay.GamePlayViewModel;
import ui.console.mainMenu.MainMenuView;
import ui.console.mainMenu.MainMenuViewModel;
import ui.console.playerSymbol.ChangePlayersSymbolsView;
import ui.console.playerSymbol.ChangePlayersSymbolsViewModel;
import ui.console.playerSymbol.SelectPlayerSymbolView;
import ui.console.playerSymbol.SelectPlayerSymbolViewModel;
import ui.console.playerType.SelectPlayerView;
import ui.console.playerType.SelectPlayerViewModel;

public class ConsoleUserInterface implements UserInterface {

  private Scanner input;
  private MainMenuView mainMenuView;
  private SelectPlayerView selectPlayerView;
  private SelectFirstPlayerView selectFirstPlayerView;
  private ChangePlayersSymbolsView changePlayersSymbolsView;
  private SelectPlayerSymbolView selectPlayerSymbolView;
  private GamePlayView gamePlayView;
  Game game;

  public ConsoleUserInterface(Scanner input) {
    this.input = input;
  }

  @Override
  public void setMainMenuView(MainMenuView view) {
    mainMenuView = view;
  }

  @Override
  public void setGamePlayView(GamePlayView view) {
    gamePlayView = view;
  }

  @Override
  public void setSelectPlayerView(SelectPlayerView view) {
    selectPlayerView = view;
  }

  @Override
  public void setSelectFirstPlayerView(SelectFirstPlayerView view) {
    selectFirstPlayerView = view;
  }

  @Override
  public void setChangePlayersSymbolsView(ChangePlayersSymbolsView view) {
    changePlayersSymbolsView = view;
  }

  @Override
  public void setSelectPlayerSymbolView(SelectPlayerSymbolView view) {
    selectPlayerSymbolView = view;
  }

  @Override
  public void launch() {
    int mainMenuChoice = launchMainMenu();

    if (mainMenuChoice == 1) {
      int playerOneType = launchSelectPlayerMenu("first");
      int playerTwoType = launchSelectPlayerMenu("second");

      int firstPlayer = launchSelectFirstPlayerMenu();
      boolean changeSymbols = launchChangePlayersSymbolsMenu();
      if (changeSymbols) {
        String playerOneSymbol = launchSelectPlayerSymbolMenu("player 1");
        String playerTwoSymbol = launchSelectPlayerSymbolMenu("player 2");
        gamePlayView.setPlayerOneSymbol(playerOneSymbol);
        gamePlayView.setPlayerTwoSymbol(playerTwoSymbol);
      }

      Player player1 = makePlayer(playerOneType, "Player 1");
      Player player2 = makePlayer(playerTwoType, "Player 2");
      game = new TicTacToe(player1, player2);
      game.setFirstPlayer(firstPlayer);

      launchGame(new TicTacToeTurnPresenter());
    }

    exit(0);
  }

  private void sendUserInputToGame(int input) {
    game.receiveUserInput(input);
  }

  private int launchMainMenu() {
    MainMenuViewModel viewModel = new MainMenuViewModel();
    ViewController controller = new ViewController(viewModel, mainMenuView);
    controller.updateView();
    return Integer.parseInt(controller.getUserInput(input));
  }

  private int launchSelectPlayerMenu(String position) {
    SelectPlayerViewModel viewModel = new SelectPlayerViewModel();
    viewModel.playerTypes = Arrays.asList("Human", "Computer (medium)", "Computer (hard)");
    viewModel.position = position;
    ViewController controller = new ViewController(viewModel, selectPlayerView);
    controller.updateView();
    return Integer.parseInt(controller.getUserInput(input));
  }

  private int launchSelectFirstPlayerMenu() {
    SelectFirstPlayerViewModel viewModel = new SelectFirstPlayerViewModel();
    ViewController controller = new ViewController(viewModel, selectFirstPlayerView);
    controller.updateView();
    return controller.getUserInput(input).equals("Y") ? 1 : 0;
  }

  private boolean launchChangePlayersSymbolsMenu() {
    ChangePlayersSymbolsViewModel viewModel = new ChangePlayersSymbolsViewModel();
    viewModel.playerOneSymbol = GamePlayView.PLAYER_ONE_SYMBOL;
    viewModel.playerTwoSymbol = GamePlayView.PLAYER_TWO_SYMBOL;
    ViewController controller = new ViewController(viewModel, changePlayersSymbolsView);
    controller.updateView();
    return controller.getUserInput(input).equals("Y");
  }

  private String launchSelectPlayerSymbolMenu(String name) {
    SelectPlayerSymbolViewModel viewModel = new SelectPlayerSymbolViewModel();
    viewModel.playerName = name;
    ViewController controller = new ViewController(viewModel, selectPlayerSymbolView);
    controller.updateView();
    return controller.getUserInput(input);
  }

  private Player makePlayer(int playerType, String name) {
    Player player;
    if (playerType == 1)
      player = new HumanPlayer(name);
    else if (playerType == 2)
      player = new ComputerPlayer(name, new MediumArtificialIntelligence());
    else if (playerType == 3)
      player = new ComputerPlayer(name, new HardArtificialIntelligence());
    else
      player = null;

    if (player == null)
      exit(1);
    return player;
  }

  boolean launchGame(TurnPresenter presenter) {
    presenter.register(this);
    boolean gameOver = false;
    while (!gameOver)
      gameOver = game.play(presenter);
    return true;
  }

  private void exit(int status) {
    System.exit(status);
  }

  @Override
  public void notifyTurnPlayed(TurnPresenter presenter) {
    GamePlayViewModel viewModel = presenter.getViewModel();
    ViewController controller = new ViewController(viewModel, gamePlayView);
    controller.updateView();
    if (viewModel.userInputIsRequired)
      sendUserInputToGame(Integer.valueOf(controller.getUserInput(input)) - 1);
  }
}
