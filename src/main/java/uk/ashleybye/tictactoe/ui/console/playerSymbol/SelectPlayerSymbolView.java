package uk.ashleybye.tictactoe.ui.console.playerSymbol;


import uk.ashleybye.tictactoe.ui.View;
import uk.ashleybye.tictactoe.ui.console.gamePlay.GamePlayView;

public class SelectPlayerSymbolView implements View<SelectPlayerSymbolViewModel> {

  @Override
  public void show(SelectPlayerSymbolViewModel viewModel) {
    System.out.print("\nEnter a new one character symbol for " + viewModel.playerName + ": ");
  }

  @Override
  public char[] getInputOptions() {
    return GamePlayView.VALID_SYMBOLS.toCharArray();
  }
}