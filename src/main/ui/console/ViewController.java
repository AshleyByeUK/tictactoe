package ui.console;

import java.util.Scanner;
import ui.View;
import ui.ViewModel;

class ViewController {

  private final View view;
  private final ViewModel viewModel;

  ViewController(ViewModel viewModel, View view) {
    this.viewModel = viewModel;
    this.view = view;
  }

  void updateView() {
    view.show(viewModel);
  }

  String getUserInput(Scanner input) {
    return InputUtilities.getUppercaseInput(input, viewModel.getInputOptions());
  }
}
