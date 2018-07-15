package ui.console;

import ui.View;
import ui.ViewModel;

public class SelectPlayerView implements View {

  @Override
  public void show(ViewModel viewModel) {
    SelectPlayerViewModel vm = (SelectPlayerViewModel) viewModel;
    System.out.println(formatMenu(vm));
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
