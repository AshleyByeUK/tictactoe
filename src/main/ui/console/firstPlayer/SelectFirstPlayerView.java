package ui.console.firstPlayer;

import ui.View;

public class SelectFirstPlayerView implements View<SelectFirstPlayerViewModel> {

  @Override
  public void show(SelectFirstPlayerViewModel viewModel) {
    System.out.println("\nPlayer 1 plays first. Swap playing order? (Y/N)\n");
  }

  @Override
  public char[] getInputOptions() {
    return "yn".toCharArray();
  }
}
