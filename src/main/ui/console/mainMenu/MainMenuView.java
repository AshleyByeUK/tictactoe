package ui.console.mainMenu;

import ui.View;
import ui.ViewModel;

public class MainMenuView implements View {

  @Override
  public void show(ViewModel viewModel) {
    MainMenuViewModel vm = (MainMenuViewModel) viewModel;
    String output = formatMainTitle(vm)
        + formatMenu(vm);
    System.out.println(output);
  }

  private String formatMainTitle(MainMenuViewModel vm) {
    return "\n\n" + vm.title + "\n"
        + "=========\n\n";
  }

  private String formatMenu(MainMenuViewModel vm) {
    return "Select an option:\n\n"
        + formatMenuChoices(vm);
  }

  private String formatMenuChoices(MainMenuViewModel vm) {
    String choices = "";
    for (int i = 0; i < vm.menuOptions.size(); i++)
      choices += String.format("%d. %s\n", i + 1, vm.menuOptions.get(i));
    return choices;
  }
}
