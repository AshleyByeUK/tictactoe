package uk.ashleybye.tictactoe.ui.console;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.game.TicTacToeGameSpy;
import uk.ashleybye.tictactoe.game.TicTacToeTurnNotificationPublisherSpy;
import uk.ashleybye.tictactoe.player.TicTacToePlayerFactory;

class ConsoleUserInterfaceTest {

  private Scanner input;
  private TicTacToeTurnNotificationPublisherSpy presenterSpy;
  private ConsoleUserInterface console;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        // Do nothing.
      }
    }));
    input = new Scanner("1");
    presenterSpy = new TicTacToeTurnNotificationPublisherSpy();
    console = new ConsoleUserInterface(input, new TicTacToePlayerFactory());
  }

  @Test
  void canReceiveViewModelFromPresenter() {
    console.game = new TicTacToeGameSpy("computer");
    boolean gameOver = console.launchGame(presenterSpy);

    assertTrue(presenterSpy.getTurnNotificationWasCalled);
    assertTrue(gameOver);
  }
}
