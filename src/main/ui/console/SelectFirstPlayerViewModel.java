package ui.console;

import ui.ViewModel;

public class SelectFirstPlayerViewModel implements ViewModel {

  @Override
  public char[] getInputOptions() {
    return "yn".toCharArray();
  }
}
