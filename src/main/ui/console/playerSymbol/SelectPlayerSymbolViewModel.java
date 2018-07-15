package ui.console.playerSymbol;

import ui.ViewModel;
import ui.console.gamePlay.GamePlayView;

public class SelectPlayerSymbolViewModel implements ViewModel {

  public String playerName = "";

  @Override
  public char[] getInputOptions() {
    return GamePlayView.VALID_SYMBOLS.toCharArray();
  }
}
