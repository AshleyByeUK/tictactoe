package uk.ashleybye.tictactoe.ui.console;


import java.util.Scanner;
import uk.ashleybye.tictactoe.game.Game;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.PlayerFactory;
import uk.ashleybye.tictactoe.game.TurnNotification;
import uk.ashleybye.tictactoe.game.TurnNotificationPublisher;
import uk.ashleybye.tictactoe.game.UserInterface;
import uk.ashleybye.tictactoe.game.impl.TurnNotificationPublisherImpl;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayViewModel;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuView;
import uk.ashleybye.tictactoe.ui.console.mainMenu.MainMenuViewModel;

public class ConsoleUserInterface implements UserInterface {

  private Scanner input;
  private PlayerFactory playerFactory;
  private ConsoleGameConfigurator gameConfigurator;

  public ConsoleUserInterface(Scanner input, PlayerFactory playerFactory) {
    gameConfigurator = new ConsoleGameConfigurator(input, playerFactory);
    this.playerFactory = playerFactory;
    this.input = input;
  }

  @Override
  public boolean launch() {
    if (launchMainMenu() == 1)
      return playTicTacToe();
    else
      return false;
  }

  private int launchMainMenu() {
    MainMenuViewModel viewModel = new MainMenuViewModel();
    MainMenuView view = new MainMenuView();
    ViewController<MainMenuView, MainMenuViewModel> controller = new ViewController<>(viewModel, view);
    controller.updateView();
    return Integer.parseInt(controller.getUserInput(input));
  }

  private boolean playTicTacToe() {
    GameOptions options = gameConfigurator.configureGame();
    Game game = Game.playTicTacToe(options, playerFactory, this);
    return launchGame(game);
  }

  private boolean launchGame(Game game) {
    TurnNotificationPublisherImpl publisher = new TurnNotificationPublisherImpl();
    publisher.subscribe(this);
    boolean gameOver = false;
    while (!gameOver)
      gameOver = game.play(publisher);
    return true;
  }

  @Override
  public void receiveTurnPlayedNotification(TurnNotificationPublisher publisher) {
    GamePlayViewModel viewModel = populateViewModel(publisher.getTurnNotification());
    GamePlayView view = new GamePlayView();
    ViewController<GamePlayView, GamePlayViewModel> controller = new ViewController<>(viewModel, view);
    controller.updateView();
  }

  @Override
  public int getPositionToPlay(TurnNotification turnNotification) {
    GamePlayViewModel viewModel = populateViewModel(turnNotification);
    GamePlayView view = new GamePlayView();
    ViewController<GamePlayView, GamePlayViewModel> controller = new ViewController<>(viewModel, view);
    controller.updateView();
    return Integer.valueOf(controller.getUserInput(input)) - 1;
  }

  private GamePlayViewModel populateViewModel(TurnNotification notification) {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    viewModel.gameState = notification.gameState;
    viewModel.currentPlayerName = notification.currentPlayerName;
    viewModel.board = notification.board;
    viewModel.gameResult = notification.gameResult;
    viewModel.lastPositionPlayed = notification.lastPositionPlayed;
    viewModel.availablePositions = notification.availablePositions;
    viewModel.playerOneSymbol = notification.players[0].getSymbol();
    viewModel.playerTwoSymbol = notification.players[1].getSymbol();
    viewModel.userInputIsRequired = notification.userInputIsRequired;
    return viewModel;
  }
}
