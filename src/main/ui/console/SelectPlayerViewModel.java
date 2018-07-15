package ui.console;

import java.util.ArrayList;
import java.util.List;
import ui.ViewModel;

public class SelectPlayerViewModel implements ViewModel {

  String position = "";
  List<String> playerTypes = new ArrayList<>();

  @Override
  public char[] getInputOptions() {
    return "123".toCharArray();
  }
}
