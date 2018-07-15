package ui.console.playerType;

import ui.View;

public class SelectPlayerView implements View<SelectPlayerViewModel> {

  @Override
  public void show(SelectPlayerViewModel viewModel) {
    System.out.println(formatMenu(viewModel));
  }

  private String formatMenu(SelectPlayerViewModel vm) {
    return "\nSelect " + vm.position + " player type:\n\n"
        + formatPlayerTypes(vm);
  }

  private String formatPlayerTypes(SelectPlayerViewModel vm) {
    String choices = "";
    for (int i = 0; i < vm.playerTypes.size(); i++)
      choices += String.format("%d. %s\n", i + 1, vm.playerTypes.get(i));
    return choices;
  }
}
