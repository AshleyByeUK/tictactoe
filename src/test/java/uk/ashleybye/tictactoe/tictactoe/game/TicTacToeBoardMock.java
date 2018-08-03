package uk.ashleybye.tictactoe.tictactoe.game;

public class TicTacToeBoardMock extends TicTacToeBoard {

  public boolean placeSymbolAtPositionWasCalled;
  public int symbolPlacedInPosition;

  private TicTacToeBoardMock() {
  }

  public static TicTacToeBoardMock configureBoard() {
    return new TicTacToeBoardMock();
  }

  public static TicTacToeBoardMock configureBoard(int[] positions) {
    TicTacToeBoardMock board = new TicTacToeBoardMock();
    board.setPositions(positions);
    return board;
  }

  public void setPositions(int[] positions) {
    super.positions = positions;
  }

  @Override
  public void placeSymbolAtPosition(int position, int player) {
    super.placeSymbolAtPosition(position, player);
    placeSymbolAtPositionWasCalled = true;
    symbolPlacedInPosition = position;
  }
}
