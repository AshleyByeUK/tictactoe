package console;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class GamePlayViewControllerTest {

  @Test
  void updatesView() {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    GamePlayViewSpy viewSpy = new GamePlayViewSpy();
    GamePlayViewController controller = new GamePlayViewController(viewModel, viewSpy);
    controller.updateView();

    assertTrue(viewSpy.showWasCalled);
  }

  @Test
  void getsUserInput() {
    GamePlayViewModel viewModel = new GamePlayViewModel();
    GamePlayView viewSpy = new GamePlayViewSpy();
    Scanner input = new Scanner("1");
    GamePlayViewController controller = new GamePlayViewController(viewModel, viewSpy);
    int result = controller.getUserInput(input);

    assertEquals(1, result);
  }
}
