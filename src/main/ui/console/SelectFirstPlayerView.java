package ui.console;

import ui.View;
import ui.ViewModel;

public class SelectFirstPlayerView implements View {

  @Override
  public void show(ViewModel viewModel) {
    System.out.println("\nPlayer 1 plays first. Swap playing order? (Y/N)\n");
  }
}
