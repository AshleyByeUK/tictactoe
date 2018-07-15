package tictactoe;

import tictactoe.game.TicTacToeGame;

public interface Game {

  static Game playTicTacToe(Player player1, Player player2, int firstPlayer) {
    TicTacToeGame game = new TicTacToeGame(player1, player2);
    game.setFirstPlayer(firstPlayer);
    return game;
  }

  boolean play(TurnPresenter presenter);

  void receiveUserInput(int input);
}
