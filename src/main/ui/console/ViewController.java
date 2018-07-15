package ui.console;

import java.util.Scanner;
import ui.View;
import ui.ViewModel;

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
