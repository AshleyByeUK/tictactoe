package tictactoe;

public class BoardMock extends Board {

  public boolean placeTokenWasCalled;
  public int tokenPlacedInPosition;

  public static BoardMock configureBoard() {
    return new BoardMock();
  }

  public static BoardMock configureBoard(int[] positions, int currentPlayer) {
    BoardMock board = new BoardMock();
    board.setPositions(positions);
    board.setCurrentPlayer(currentPlayer);
    board.setNextPlayer(currentPlayer == 0 ? 1 : 0);
    return board;
  }

  private BoardMock() {
  }

  public void setPositions(int[] positions) {
    super.positions = positions;
  }

  @Override
  public void placeToken(int position) {
    super.placeToken(position);
    placeTokenWasCalled = true;
    tokenPlacedInPosition = position;
  }
}
