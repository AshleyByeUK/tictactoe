package ui.console;

import static ui.console.InputUtilities.getIntegerInput;
import static ui.console.InputUtilities.getStringInput;
import static ui.console.InputUtilities.getYesNoInput;

import computer.ComputerPlayer;
import computer.HardArtificialIntelligence;
import computer.MediumArtificialIntelligence;
import human.HumanPlayer;
import java.util.Scanner;
import tictactoe.Game;
import tictactoe.Player;
import tictactoe.TurnPresenter;
import ui.UserInterface;
import ui.View;

public class ConsoleUserInterface implements UserInterface {

  private Scanner input;
  private View gamePlayView;
  Game game;

  public ConsoleUserInterface(Scanner input) {
    this.input = input;
  }

  @Override
  public void setGamePlayView(View gamePlayView) {
    this.gamePlayView = gamePlayView;
  }

  @Override
  public void notifyTurnPlayed(TurnPresenter presenter) {
    GamePlayViewModel viewModel = presenter.getViewModel();
    ViewController controller = new ViewController(viewModel, gamePlayView);
    controller.updateView();
    if (viewModel.userInputIsRequired)
      sendUserInputToGame(Integer.valueOf(controller.getUserInput(input)) - 1);
  }

  private void sendUserInputToGame(int input) {
    game.receiveUserInput(input);
  }

  @Override
  public void launch() {
    displayMainMenuOptions();
    int mainMenuChoice = getIntegerInput(input, 1, 2);

    if (mainMenuChoice == 1) {
      Player player1 = configurePlayer("Player 1", "first");
      Player player2 = configurePlayer("Player 2", "second");

      game = new Game(player1, player2);
      configurePlayingOrder(game);
      configureGamePlayView(player1, player2);

      TurnPresenter presenter = new TurnPresenter();
      playGame(presenter);
    }

    exit("\nGood bye.\n", 0);
  }

  private void displayMainMenuOptions() {
    System.out.println("\n\nTicTacToe");
    System.out.println("=========\n");
    System.out.println("Select an option:\n");
    System.out.println("1. Play a game.");
    System.out.println("2. Exit.");
    System.out.println();
  }

  private Player configurePlayer(String name, String order) {
    displayChoosePlayerTypeMenu(order);
    int type = getIntegerInput(input, 1, 3);
    return makePlayer(type, name);
  }

  private void displayChoosePlayerTypeMenu(String order) {
    System.out.println("\nSelect " + order + " player type:\n");
    System.out.println("1. Human");
    System.out.println("2. Computer (medium)");
    System.out.println("3. Computer (hard)");
    System.out.println();
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
      exit("\nUnknown error, exiting.", 1);
    return player;
  }

  private void configurePlayingOrder(Game game) {
    displayChangePlayingOrderMenu();
    boolean swapFirstPlayer = getYesNoInput(input);
    swapPlayingOrder(swapFirstPlayer, game);
  }

  private void displayChangePlayingOrderMenu() {
    System.out.println("\nPlayer 1 plays first. Swap playing order? (Y/N)\n");
  }

  private void swapPlayingOrder(boolean swapPlayingOrder, Game game) {
    if (swapPlayingOrder)
      game.setFirstPlayer(1);
  }

  private void configureGamePlayView(Player player1, Player player2) {
    displayChangePlayersTokensMenu(player1, player2);
    boolean changePlayerTokens = getYesNoInput(input);
    updatePlayerTokens(changePlayerTokens);
  }

  private void displayChangePlayersTokensMenu(Player player1, Player player2) {
    System.out.println(String.format("\n%s's symbol: %s", player1.getName(), GamePlayView.PLAYER_ONE_TOKEN));
    System.out.println(String.format("%s's symbol: %s", player2.getName(), GamePlayView.PLAYER_TWO_TOKEN));
    System.out.println("\nWould you like to change these symbols? (Y/N)\n");
  }

  private void updatePlayerTokens(boolean change) {
    if (change) {
      String playerOneToken = GamePlayView.PLAYER_ONE_TOKEN;
      String playerTwoToken = GamePlayView.PLAYER_TWO_TOKEN;
      boolean changed = false;
      while (!changed) {
        System.out.print("\nEnter a new one character symbol for player 1 (" + playerOneToken + "): ");
        playerOneToken = getStringInput(input, 1).toUpperCase();
        System.out.print("\nEnter a new one character symbol for player 2 (" + playerTwoToken + "): ");
        playerTwoToken = getStringInput(input, 1).toUpperCase();
        if (playerOneToken.equals(playerTwoToken))
          System.out.println("\nPlayer's symbols cannot be the same.");
        else
          changed = true;
      }
      ((GamePlayView) gamePlayView).setPlayerOneToken(playerOneToken);
      ((GamePlayView) gamePlayView).setPlayerTwoToken(playerTwoToken);
    }
  }

  boolean playGame(TurnPresenter presenter) {
    presenter.register(this);
    boolean gameOver = false;
    while (!gameOver)
      gameOver = game.play(presenter);
    return true;
  }

  private void exit(String message, int status) {
    System.out.println(message);
    System.exit(status);
  }
}
