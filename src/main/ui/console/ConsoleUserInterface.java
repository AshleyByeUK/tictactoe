package ui.console;


import java.util.Scanner;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.PlayerFactory;
import tictactoe.TurnNotificationPublisher;
import tictactoe.game.TicTacToeGame;
import tictactoe.game.TicTacToeTurnNotification;
import tictactoe.game.TicTacToeTurnNotificationPublisher;
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

  private final String YES = "Y";
  private final String PLAYER_ONE_NAME = "Player 1";
  private final String PLAYER_TWO_NAME = "Player 2";
  private final String FIRST_PLAYER = "first";
  private final String SECOND_PLAYER = "second";

  TicTacToeGame game;
  private Scanner input;
  private PlayerFactory playerFactory;
  private MainMenuView mainMenuView;
  private SelectPlayerView selectPlayerView;
  private SelectFirstPlayerView selectFirstPlayerView;
  private ChangePlayersSymbolsView changePlayersSymbolsView;
  private SelectPlayerSymbolView selectPlayerSymbolView;
  private GamePlayView gamePlayView;

  public ConsoleUserInterface(Scanner input) {
    this.input = input;
  }

  @Override
  public void setPlayerFactory(PlayerFactory playerFactory) {
    this.playerFactory = playerFactory;
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
  public boolean launch() {
    if (launchMainMenu() == 1)
      return playTicTacToe();
    else
      return false;
  }

  private boolean playTicTacToe() {
    configureGame();
    return launchGame(new TicTacToeTurnNotificationPublisher());
  }

  private void configureGame() {
    String playerOneType = launchSelectPlayerMenu(FIRST_PLAYER);
    String playerTwoType = launchSelectPlayerMenu(SECOND_PLAYER);
    int firstPlayer = launchSelectFirstPlayerMenu();
    boolean changeSymbols = launchChangePlayersSymbolsMenu();

    if (changeSymbols) {
      String playerOneSymbol = launchSelectPlayerSymbolMenu(PLAYER_ONE_NAME);
      String playerTwoSymbol = launchSelectPlayerSymbolMenu(PLAYER_TWO_NAME);
      gamePlayView.setPlayerOneSymbol(playerOneSymbol);
      gamePlayView.setPlayerTwoSymbol(playerTwoSymbol);
    }

    Player player1 = playerFactory.make(playerOneType, PLAYER_ONE_NAME);
    Player player2 = playerFactory.make(playerTwoType, PLAYER_TWO_NAME);
    game = Game.playTicTacToe(player1, player2, firstPlayer);
  }

  private int launchMainMenu() {
    MainMenuViewModel viewModel = new MainMenuViewModel();
    ViewController controller = new ViewController(viewModel, mainMenuView);
    controller.updateView();
    return Integer.parseInt(controller.getUserInput(input));
  }

  private String launchSelectPlayerMenu(String position) {
    SelectPlayerViewModel viewModel = new SelectPlayerViewModel();
    viewModel.playerTypes = playerFactory.listPlayerTypes();
    viewModel.position = position;
    ViewController controller = new ViewController(viewModel, selectPlayerView);
    controller.updateView();
    int choice = Integer.parseInt(controller.getUserInput(input));
    return playerFactory.listPlayerTypes().get(choice - 1);
  }

  private int launchSelectFirstPlayerMenu() {
    SelectFirstPlayerViewModel viewModel = new SelectFirstPlayerViewModel();
    ViewController controller = new ViewController(viewModel, selectFirstPlayerView);
    controller.updateView();
    return controller.getUserInput(input).equals(YES) ? 1 : 0;
  }

  private boolean launchChangePlayersSymbolsMenu() {
    ChangePlayersSymbolsViewModel viewModel = new ChangePlayersSymbolsViewModel();
    viewModel.playerOneSymbol = GamePlayView.PLAYER_ONE_SYMBOL;
    viewModel.playerTwoSymbol = GamePlayView.PLAYER_TWO_SYMBOL;
    ViewController controller = new ViewController(viewModel, changePlayersSymbolsView);
    controller.updateView();
    return controller.getUserInput(input).equals(YES);
  }

  private String launchSelectPlayerSymbolMenu(String name) {
    SelectPlayerSymbolViewModel viewModel = new SelectPlayerSymbolViewModel();
    viewModel.playerName = name;
    ViewController controller = new ViewController(viewModel, selectPlayerSymbolView);
    controller.updateView();
    return controller.getUserInput(input);
  }

  boolean launchGame(TicTacToeTurnNotificationPublisher publisher) {
    publisher.subscribe(this);
    boolean gameOver = false;
    while (!gameOver)
      gameOver = game.play(publisher);
    return true;
  }

  @Override
  public void receiveTurnPlayedNotification(TurnNotificationPublisher publisher) {
    GamePlayViewModel viewModel = populateViewModel((TicTacToeTurnNotification) publisher.getTurnNotification());
    ViewController controller = new ViewController(viewModel, gamePlayView);
    controller.updateView();
    if (viewModel.userInputIsRequired)
      sendUserInputToGame(Integer.valueOf(controller.getUserInput(input)) - 1);
  }

  private GamePlayViewModel populateViewModel(TicTacToeTurnNotification notification) {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    viewModel.gameState = notification.gameState;
    viewModel.turnResult = notification.turnResult;
    viewModel.currentPlayerName = notification.currentPlayerName;
    viewModel.board = notification.board;
    viewModel.gameResult = notification.gameResult;
    viewModel.lastPositionPlayed = notification.lastPositionPlayed;
    viewModel.availablePositions = notification.availablePositions;
    viewModel.userInputIsRequired = !notification.turnResult.equals(TicTacToeGame.TURN_RESULT_TURN_COMPLETE);
    viewModel.userPositionIsTaken = notification.turnResult.equals(TicTacToeGame.TURN_RESULT_POSITION_TAKEN);
    return viewModel;
  }

  private void sendUserInputToGame(int input) {
    game.receiveUserInput(input);
  }
}
