package console;

public class ConsoleGamePlayView implements GamePlayView {

  @Override
  public void show(GamePlayViewModel viewModel) {
    System.out.println("Playing TicTacToe");
  }
}
