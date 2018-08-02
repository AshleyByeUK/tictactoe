package uk.ashleybye.tictactoe.ui;

public interface View<T extends ViewModel> {

  void show(T viewModel);

  char[] getInputOptions();
}
