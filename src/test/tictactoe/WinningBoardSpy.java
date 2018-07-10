package tictactoe;

public class WinningBoardSpy extends BoardSpy {

  public WinningBoardSpy() {
    positions = new int[]{1, 1, -1, 0, 0, -1, 0, -1, -1};
    currentPlayer = 1;
  }
}
