package tictactoe;

public interface Game {

  void setFirstPlayer(int player);

  boolean play(TurnPresenter presenter);

  void receiveUserInput(int input);
}
