package uk.ashleybye.tictactoe.ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayViewModel;


public class ViewControllerTest {

  @BeforeAll
  static void before() {
    System.setOut(new PrintStream(new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        // Do nothing.
      }
    }));
  }

  @Test
  void updatesView() {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    GamePlayViewSpy viewSpy = new GamePlayViewSpy();
    ViewController<GamePlayViewSpy, GamePlayViewModel> controller = new ViewController<>(viewModel, viewSpy);
    controller.updateView();

    assertTrue(viewSpy.showWasCalled);
  }

  @Test
  void getsUserInput() {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    GamePlayView viewSpy = new GamePlayViewSpy();
    Scanner input = new Scanner("1");
    ViewController<GamePlayView, GamePlayViewModel> controller = new ViewController<>(viewModel, viewSpy);
    String result = controller.getUserInput(input);

    assertEquals("1", result);
  }
}
