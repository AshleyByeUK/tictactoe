package uk.ashleybye.tictactoe.ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class InputUtilitiesTest {

  private final char[] NUMBERS = "123456789".toCharArray();
  private final char[] SYMBOLS = "123456789abcdefghijklmnopqrstuvwxyz@£#$%^&*()?".toCharArray();
  private final char[] YES_NO = "yn".toCharArray();

  @BeforeAll
  static void before() {
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        // Do nothing.
      }
    }));
  }

  private void assertInvalidInteger(String input) {
    assertFalse(InputUtilities.isValidInput(input, NUMBERS));
  }

  private void assertValidInteger(String input) {
    assertTrue(InputUtilities.isValidInput(input, NUMBERS));
  }

  private void assertInvalidSymbol(String input) {
    assertFalse(InputUtilities.isValidInput(input, SYMBOLS));
  }

  private void assertValidSymbol(String input) {
    assertTrue(InputUtilities.isValidInput(input, SYMBOLS));
  }

  private void assertInvalidYesNo(String input) {
    assertFalse(InputUtilities.isValidInput(input, YES_NO));
  }

  private void assertValidYesNo(String input) {
    assertTrue(InputUtilities.isValidInput(input, YES_NO));
  }

  @Test
  void invalidIntegerInput() {
    assertInvalidInteger("");
    assertInvalidInteger(" ");
    assertInvalidInteger("A");
    assertInvalidInteger("£");
    assertInvalidInteger("-1");
    assertInvalidInteger("12");
    assertInvalidInteger("12 34");
    assertInvalidInteger("invalid input");
  }

  @Test
  void validIntegerInput() {
    assertValidInteger("1");
    assertValidInteger("9");
  }

  @Test
  void getValidIntegerInput() {
    assertEquals("Y", InputUtilities.getUppercaseInput(new Scanner("Y"), YES_NO));
  }

  @Test
  void invalidSymbolInput() {
    assertInvalidSymbol("");
    assertInvalidSymbol("ab");
    assertInvalidSymbol("12");
    assertInvalidSymbol("`");
  }

  @Test
  void validSymbolInput() {
    assertValidSymbol("x");
    assertValidSymbol("X");
    assertValidSymbol("o");
    assertValidSymbol("O");
    assertValidSymbol("1");
    assertValidSymbol("?");
  }

  @Test
  void getValidSymbolInput() {
    assertEquals("X", InputUtilities.getUppercaseInput(new Scanner("x"), SYMBOLS));
    assertEquals("1", InputUtilities.getUppercaseInput(new Scanner("1"), SYMBOLS));
    assertEquals("?", InputUtilities.getUppercaseInput(new Scanner("?"), SYMBOLS));
  }

  @Test
  void invalidYesNoInput() {
    assertInvalidYesNo("0");
    assertInvalidYesNo("1");
    assertInvalidYesNo("t");
    assertInvalidYesNo("f");
    assertInvalidYesNo("true");
    assertInvalidYesNo("false");
  }

  @Test
  void validYesNoInput() {
    assertValidYesNo("Y");
    assertValidYesNo("y");
    assertValidYesNo("N");
    assertValidYesNo("n");
  }

  @Test
  void getValidYesNoInput() {
    assertEquals("Y", InputUtilities.getUppercaseInput(new Scanner("y"), YES_NO));
  }
}
