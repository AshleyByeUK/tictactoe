package tictactoe.game;

public class TicTacToeBoardMock extends TicTacToeBoard {

  public boolean placeTokenWasCalled;
  public int tokenPlacedInPosition;

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

  private TicTacToeBoardMock() {
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
