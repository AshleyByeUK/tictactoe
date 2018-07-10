package tictactoe;

public class BoardSpy extends Board {

  public boolean placeTokenCalled;
  public int tokenPlacedInPosition;

  @Override
  public void placeToken(int token) {
    placeTokenCalled = true;
    tokenPlacedInPosition = token;
  }
}
