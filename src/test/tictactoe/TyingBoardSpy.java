package tictactoe;

public class TyingBoardSpy extends BoardSpy {

  public TyingBoardSpy() {
    positions = new int[]{0, 0, -1, 1, 1, -1, -1, -1, 0};
    currentPlayer = 1;
  }
}
