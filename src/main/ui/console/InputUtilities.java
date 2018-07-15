package ui.console;

import java.util.Scanner;

class InputUtilities {

  static boolean showInputPrompt = true;

  static Integer getIntegerInput(Scanner input, int minValue, int maxValue) {
    displayInputPrompt();
    String value = "";
    boolean valid = false;
    while (!valid) {
      value = input.nextLine();
      valid = InputUtilities.isValidIntegerInput(value, minValue, maxValue);
      if (!valid) {
        System.out.println("\nPlease enter a whole number between " + minValue + " and " + maxValue + ".\n");
        displayInputPrompt();
      }
    }
    return Integer.parseInt(value);
  }

  static boolean isValidIntegerInput(String input, int minValue, int maxValue) {
    boolean isValid = false;
    try {
      int value = Integer.parseInt(input);
      isValid = (value >= minValue && value <= maxValue);
    } catch (NumberFormatException e) {
      // Do nothing.
    }
    return isValid;
  }

  static String getStringInput(Scanner input, int maxLength) {
    displayInputPrompt();
    String value = "";
    boolean valid = false;
    while (!valid) {
      value = input.nextLine();
      valid = InputUtilities.isValidStringInput(value, maxLength);
      if (!valid) {
        System.out.println("\nPlease enter no more than " + maxLength
            + (maxLength == 1 ? " character.\n" : " characters.\n"));
        displayInputPrompt();
      }
    }
    return value;
  }

  static boolean isValidStringInput(String input, int maxLength) {
    return !input.isEmpty() && input.length() <= maxLength;
  }

  static boolean getYesNoInput(Scanner input) {
    displayInputPrompt();
    String value = "";
    boolean valid = false;
    while (!valid) {
      value = input.nextLine();
      valid = InputUtilities.isValidYesNoInput(value);
      if (!valid) {
        System.out.println("\nPlease enter Y/N.\n");
        displayInputPrompt();
      }
    }
    return value.equalsIgnoreCase("Y");
  }

  static boolean isValidYesNoInput(String input) {
    return input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N");
  }

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
    if (showInputPrompt)
      System.out.print("> ");
  }
}
