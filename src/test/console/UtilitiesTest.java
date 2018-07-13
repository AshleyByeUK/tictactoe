package console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UtilitiesTest {

  @BeforeAll
  static void before() {
    Utilities.showInputPrompt = false;
  }

  @Test
  void invalidIntegerInput() {
    assertFalse(Utilities.isValidIntegerInput("", 1, 1));
    assertFalse(Utilities.isValidIntegerInput(" ", 1, 1));
    assertFalse(Utilities.isValidIntegerInput("A", 1, 1));
    assertFalse(Utilities.isValidIntegerInput("£", 1, 1));
    assertFalse(Utilities.isValidIntegerInput("-1", 1, 8));
    assertFalse(Utilities.isValidIntegerInput("9", 1, 8));
    assertFalse(Utilities.isValidIntegerInput("12 34", 1, 12));
    assertFalse(Utilities.isValidIntegerInput("invalid input", 1, 1));
  }

  @Test
  void validIntegerInput() {
    assertTrue(Utilities.isValidIntegerInput("0", 0, 1));
    assertTrue(Utilities.isValidIntegerInput("10", 1, 10));
  }

  @Test
  void getValidIntegerInput() {
    assertEquals(10, Utilities.getIntegerInput(new Scanner("10"), 1, 10));
  }

  @Test
  void invalidStringInput() {
    assertFalse(Utilities.isValidStringInput("", 0));
    assertFalse(Utilities.isValidStringInput("a", 0));
    assertFalse(Utilities.isValidStringInput("", 1));
    assertFalse(Utilities.isValidStringInput("ab", 1));
    assertFalse(Utilities.isValidStringInput("abc", 2));
  }

  @Test
  void validStringInput() {
    assertTrue(Utilities.isValidStringInput("a", 1));
    assertTrue(Utilities.isValidStringInput("a", 2));
    assertTrue(Utilities.isValidStringInput("ab", 2));
  }

  @Test
  void getValidStringInput() {
    assertEquals("valid", Utilities.getStringInput(new Scanner("valid"), 5));
  }

  @Test
  void invalidYesNoInput() {
    assertFalse(Utilities.isValidYesNoInput("0"));
    assertFalse(Utilities.isValidYesNoInput("1"));
    assertFalse(Utilities.isValidYesNoInput("t"));
    assertFalse(Utilities.isValidYesNoInput("f"));
    assertFalse(Utilities.isValidYesNoInput("true"));
    assertFalse(Utilities.isValidYesNoInput("false"));
  }

  @Test
  void validYesNoInput() {
    assertTrue(Utilities.isValidYesNoInput("Y"));
    assertTrue(Utilities.isValidYesNoInput("N"));
    assertTrue(Utilities.isValidYesNoInput("y"));
    assertTrue(Utilities.isValidYesNoInput("n"));
  }

  @Test
  void getValidYesNoInput() {
    assertTrue(Utilities.getYesNoInput(new Scanner("Y")));
  }
}
