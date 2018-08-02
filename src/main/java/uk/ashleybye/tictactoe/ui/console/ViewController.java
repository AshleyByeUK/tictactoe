package uk.ashleybye.tictactoe.ui.console;

import java.util.Scanner;
import uk.ashleybye.tictactoe.ui.View;
import uk.ashleybye.tictactoe.ui.ViewModel;

class ViewController<S extends View<T>, T extends ViewModel> {

  private final S view;
  private final T viewModel;

  ViewController(T viewModel, S view) {
    this.viewModel = viewModel;
    this.view = view;
  }

  void updateView() {
    view.show(viewModel);
  }

  String getUserInput(Scanner input) {
    return InputUtilities.getUppercaseInput(input, view.getInputOptions());
  }
}
