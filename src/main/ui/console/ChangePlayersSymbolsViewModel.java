package ui.console;

import ui.ViewModel;

public class ChangePlayersSymbolsViewModel implements ViewModel {

  String playerOneSymbol;
  String playerTwoSymbol;

  @Override
  public char[] getInputOptions() {
    return GamePlayView.VALID_SYMBOLS.toCharArray();
  }
}
