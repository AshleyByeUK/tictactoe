package ui.console.playerSymbol;

import ui.ViewModel;

public class ChangePlayersSymbolsViewModel implements ViewModel {

  public String playerOneSymbol;
  public String playerTwoSymbol;

  @Override
  public char[] getInputOptions() {
    return "yn".toCharArray();
  }
}
