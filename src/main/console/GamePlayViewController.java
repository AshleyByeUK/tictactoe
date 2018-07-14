package console;

import java.util.Scanner;

public class GamePlayViewController {

  private final GamePlayView view;
  private GamePlayViewModel viewModel;

  public GamePlayViewController(GamePlayViewModel viewModel, GamePlayView view) {
    this.viewModel = viewModel;
    this.view = view;
  }

  public void updateView() {
    view.show(viewModel);
  }

  public int getUserInput(Scanner input) {
    return Utilities.getIntegerInput(input, 1, 9);
  }
}
