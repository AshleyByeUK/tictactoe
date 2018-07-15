package ui;

public interface View<T extends ViewModel> {

  void show(T viewModel);
}
