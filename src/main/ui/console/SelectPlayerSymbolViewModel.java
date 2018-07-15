package ui.console;

import ui.ViewModel;

public class SelectPlayerSymbolViewModel implements ViewModel {

  public String playerName = "";

  @Override
  public char[] getInputOptions() {
    return GamePlayView.VALID_SYMBOLS.toCharArray();
  }
}
