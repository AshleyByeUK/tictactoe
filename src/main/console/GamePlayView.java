package console;

public class GamePlayView {

  private String gameState;

  public void setGameState(String gameState) {
    this.gameState = gameState;
  }

  public void show() {
    System.out.println("Playing TicTacToe");
    System.out.println(gameState);
  }
}
