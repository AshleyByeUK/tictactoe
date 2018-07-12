package computer;

import tictactoe.Board;

public class BoardMock extends Board {

  public int positionPlayed;

  public void setPositions(int[] positions) {
    super.positions = positions;
  }

  @Override
  public void placeToken(int position) {
    super.placeToken(position);
    positionPlayed = position;
  }
}
