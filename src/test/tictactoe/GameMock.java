package tictactoe;

public class GameMock extends Game {

  public GameMock(Player player1, Player player2, UserInterface userInterface) {
    super(player1, player2, userInterface);
  }

  public void setBoard(int[] board) {
    super.board = board;
  }
}
