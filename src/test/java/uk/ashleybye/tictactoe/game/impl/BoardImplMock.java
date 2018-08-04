package uk.ashleybye.tictactoe.game.impl;

public class BoardImplMock extends BoardImpl {

  public boolean placeSymbolAtPositionWasCalled;
  public int symbolPlacedInPosition;

  private BoardImplMock() {
  }

  public static BoardImplMock configureBoard() {
    return new BoardImplMock();
  }

  public static BoardImplMock configureBoard(int[] positions) {
    BoardImplMock board = new BoardImplMock();
    board.setPositions(positions);
    return board;
  }

  private void setPositions(int[] positions) {
    super.positions = positions;
  }

  @Override
  public void placeSymbolAtPosition(int position, int player) {
    super.placeSymbolAtPosition(position, player);
    placeSymbolAtPositionWasCalled = true;
    symbolPlacedInPosition = position;
  }
}
