package ui.console.playerSymbol;

import ui.View;
import ui.ViewModel;

public class SelectPlayerSymbolView implements View {

  @Override
  public void show(ViewModel viewModel) {
    SelectPlayerSymbolViewModel vm = (SelectPlayerSymbolViewModel) viewModel;
    System.out.print("\nEnter a new one character symbol for " + vm.playerName + ": ");
  }
}
