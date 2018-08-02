package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;

class InputUtilities {

  static String getUppercaseInput(Scanner input, char[] validInputs) {
    displayInputPrompt();
    String value = "";
    boolean valid = false;
    while (!valid) {
      value = input.nextLine();
      valid = isValidInput(value, validInputs);
      if (!valid) {
        System.out.println("\nResponse is not valid, please try again.\n");
        displayInputPrompt();
      }
    }
    return value.toUpperCase();
  }

  static boolean isValidInput(String value, char[] validInputs) {
    for (char c : validInputs)
      if (value.equalsIgnoreCase(String.valueOf(c)))
        return true;
    return false;
  }

  private static void displayInputPrompt() {
    System.out.print("> ");
  }
}
