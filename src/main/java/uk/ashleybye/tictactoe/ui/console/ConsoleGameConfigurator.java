package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;
import uk.ashleybye.tictactoe.game.GameOptions;
import uk.ashleybye.tictactoe.game.PlayerFactory;

public class ConsoleGameConfigurator {

  private final Scanner input;
  private final PlayerFactory playerFactory;
  private final String YES = "Y";
  private final String PLAYER_ONE_NAME = "Player 1";
  private final String PLAYER_TWO_NAME = "Player 2";
  private final String PLAYER_ONE_SYMBOL = "X";
  private final String PLAYER_TWO_SYMBOL = "O";
  private final String FIRST_PLAYER = "first";
  private final String SECOND_PLAYER = "second";

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
    options.setPlayerOneType(selectPlayerType(FIRST_PLAYER));
    options.setPlayerTwoType(selectPlayerType(SECOND_PLAYER));
    options.setFirstPlayer(selectFirstPlayer());

    if (selectChangeSymbols()) {
      boolean changed = false;
      while (!changed) {
        options.setPlayerOneSymbol(selectPlayerSymbol(PLAYER_ONE_NAME));
        options.setPlayerTwoSymbol(selectPlayerSymbol(PLAYER_TWO_NAME));

        if (!symbolsDiffer(options))
          showPlayerSymbolsMustDifferMessage();
        else
          changed = true;
      }
    }

    return options;
  }

  private String selectPlayerType(String position) {
    String display = "\nSelect " + position + " player type:\n\n";
    char[] inputOptions = new char[playerFactory.listPlayerTypes().size()];
    for (int i = 0; i < playerFactory.listPlayerTypes().size(); i++) {
      inputOptions[i] = Character.forDigit(i + 1, 10);
      display += String.format("%d. %s\n", i + 1, playerFactory.listPlayerTypes().get(i));
    }
    display += "\n";

    System.out.print(display);
    int choice = Integer.parseInt(InputUtilities.getUppercaseInput(input, inputOptions, display));
    return playerFactory.listPlayerTypes().get(choice - 1);
  }

  private int selectFirstPlayer() {
    String display = "\nPlayer 1 plays first. Swap playing order? (Y/N)\n\n";

    System.out.print(display);
    String choice = InputUtilities.getUppercaseInput(input, "yn".toCharArray(), display);
    return choice.equals(YES) ? 1 : 0;
  }

  private boolean selectChangeSymbols() {
    String display = String.format("\n%s's symbol: %s", PLAYER_ONE_NAME, PLAYER_ONE_SYMBOL)
        + String.format("\n%s's symbol: %s", PLAYER_TWO_NAME, PLAYER_TWO_SYMBOL)
        + "\n\nWould you like to change these symbols? (Y/N)\n\n";

    System.out.print(display);
    String choice = InputUtilities.getUppercaseInput(input, "yn".toCharArray(), display);
    return choice.equals(YES);
  }

  private String selectPlayerSymbol(String name) {
    String display = "\nEnter a new one character symbol for " + name + ":\n\n";

    System.out.print(display);
    return InputUtilities.getUppercaseInput(input, ConsoleGamePlayBoundary.VALID_SYMBOLS.toCharArray(), display);
  }

  boolean symbolsDiffer(GameOptions options) {
    return !options.getPlayerOneSymbol().equalsIgnoreCase(options.getPlayerTwoSymbol());
  }

  private void showPlayerSymbolsMustDifferMessage() {
    System.out.print("\nPlayer symbols cannot be the same for both players.\n\n");
  }
}
