package uk.ashleybye.tictactoe.tictactoe;


import uk.ashleybye.tictactoe.tictactoe.game.TicTacToeGame;

public interface Game<T extends TurnNotificationPublisher> {

  static TicTacToeGame playTicTacToe(Player player1, Player player2, int firstPlayer) {
    TicTacToeGame game = new TicTacToeGame(player1, player2);
    game.setFirstPlayer(firstPlayer);
    return game;
  }

  boolean play(T publisher);

//  void receiveUserInput(int input);
}
