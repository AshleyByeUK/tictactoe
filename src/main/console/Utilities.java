package console;

import java.util.Scanner;

class Utilities {

  static boolean showInputPrompt = true;

  static int getIntegerInput(Scanner input, int minValue, int maxValue) {
    displayInputPrompt();
    String value = "";
    boolean valid = false;
    while (!valid) {
      value = input.nextLine();
      valid = Utilities.isValidIntegerInput(value, minValue, maxValue);
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
      valid = Utilities.isValidStringInput(value, maxLength);
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
      valid = Utilities.isValidYesNoInput(value);
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

  private static void displayInputPrompt() {
    if (showInputPrompt)
      System.out.print("> ");
  }
}
