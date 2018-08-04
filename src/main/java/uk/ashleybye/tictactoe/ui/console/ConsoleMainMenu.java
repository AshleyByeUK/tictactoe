package uk.ashleybye.tictactoe.ui.console;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class ConsoleMainMenu {

  private final String TITLE = "TicTacToe";
  private final List<String> MENU_OPTIONS = Arrays.asList("Play a game.", "Exit.");
  private final Scanner input;

  ConsoleMainMenu(Scanner input) {
    this.input = input;
  }

  int selectOption() {
    System.out.print("\n\n" + TITLE + "\n" + "=========\n\n");

    String display = "\nSelect an option:\n\n";
    char[] inputOptions = new char[MENU_OPTIONS.size()];
    for (int i = 0; i < MENU_OPTIONS.size(); i++) {
      inputOptions[i] = Character.forDigit(i + 1, 10);
      display += String.format("%d. %s\n", i + 1, MENU_OPTIONS.get(i));
    }
    display += "\n";

    System.out.print(display);
    int choice = Integer.parseInt(InputUtilities.getUppercaseInput(input, inputOptions, display));
    return choice - 1;
  }
}
