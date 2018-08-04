package uk.ashleybye.tictactoe;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.player.PlayerFactoryImpl;
import uk.ashleybye.tictactoe.ui.UserInterface;
import uk.ashleybye.tictactoe.ui.console.ConsoleUserInterface;

class TicTacToeTest {

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        // Do nothing.
      }
    }));
  }

  @Test
  void humanVsHumanEndToEndTest() {
    Scanner input = new Scanner("1\n1\n1\ny\ny\na\na\na\nb\n1\n2\n3\n4\n5\n6\n8\n7\n9\n");
    UserInterface ui = new ConsoleUserInterface(input, new PlayerFactoryImpl());
    boolean gameOver = ui.launch();

    assertTrue(gameOver);
  }

  @Test
  void mediumVsHardComputerEndToEndTest() {
    Scanner input = new Scanner("1\n2\n3\nn\nn\n");
    UserInterface ui = new ConsoleUserInterface(input, new PlayerFactoryImpl());
    boolean gameOver = ui.launch();

    assertTrue(gameOver);
  }
}
