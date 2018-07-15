package ui.console.playerSymbol;

import ui.View;

public class ChangePlayersSymbolsView implements View<ChangePlayersSymbolsViewModel> {

  @Override
  public void show(ChangePlayersSymbolsViewModel viewModel) {
    String output = formatPlayersCurrentSymbols(viewModel)
        + formatChangeSymbolsQuestion();

    System.out.println(output);
  }

  private String formatPlayersCurrentSymbols(ChangePlayersSymbolsViewModel vm) {
    return String.format("\nPlayer 1's symbol: %s", vm.playerOneSymbol)
        + String.format("\nPlayer 2's symbol: %s", vm.playerTwoSymbol);
  }

  private String formatChangeSymbolsQuestion() {
    return "\n\nWould you like to change these symbols? (Y/N)\n";
  }
}
