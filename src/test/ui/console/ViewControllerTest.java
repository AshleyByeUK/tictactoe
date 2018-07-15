package ui.console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ViewControllerTest {

  @BeforeAll
  static void before() {
    InputUtilities.showInputPrompt = false;
  }

  @Test
  void updatesView() {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    GamePlayViewSpy viewSpy = new GamePlayViewSpy();
    ViewController controller = new ViewController(viewModel, viewSpy);
    controller.updateView();

    assertTrue(viewSpy.showWasCalled);
  }

  @Test
  void getsUserInput() {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    GamePlayView viewSpy = new GamePlayViewSpy();
    Scanner input = new Scanner("1");
    ViewController controller = new ViewController(viewModel, viewSpy);
    String result = controller.getUserInput(input);

    assertEquals("1", result);
  }
}
