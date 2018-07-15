package ui.console.mainMenu;

import ui.View;

public class MainMenuView implements View<MainMenuViewModel> {

  @Override
  public void show(MainMenuViewModel viewModel) {
    String output = formatMainTitle(viewModel)
        + formatMenu(viewModel);
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

  @Override
  public char[] getInputOptions() {
    return "12".toCharArray();
  }
}
