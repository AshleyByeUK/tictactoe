package ui.console.playerSymbol;

import ui.View;

public class SelectPlayerSymbolView implements View<SelectPlayerSymbolViewModel> {

  @Override
  public void show(SelectPlayerSymbolViewModel viewModel) {
    System.out.print("\nEnter a new one character symbol for " + viewModel.playerName + ": ");
  }
}
