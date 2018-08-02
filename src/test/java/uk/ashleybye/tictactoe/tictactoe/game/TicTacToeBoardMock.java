package uk.ashleybye.tictactoe.tictactoe.game;

public class TicTacToeBoardMock extends TicTacToeBoard {

  public boolean placeSymbolAtPositionWasCalled;
  public int symbolPlacedInPosition;

  private TicTacToeBoardMock() {
  }

  public static TicTacToeBoardMock configureBoard() {
    return new TicTacToeBoardMock();
  }

  public static TicTacToeBoardMock configureBoard(int[] positions, int currentPlayer) {
    TicTacToeBoardMock board = new TicTacToeBoardMock();
    board.setPositions(positions);
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(currentPlayer == 0 ? 1 : 0);
    return board;
  }

  public void setPositions(int[] positions) {
    super.positions = positions;
  }

  @Override
  public void placeSymbolAtPosition(int position) {
    super.placeSymbolAtPosition(position);
    placeSymbolAtPositionWasCalled = true;
    symbolPlacedInPosition = position;
  }
}
