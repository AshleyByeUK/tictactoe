package computer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import tictacttoe.Game;
import tictacttoe.Player;

public class ComputerPlayer implements Player {

  private final String name;
  private final Random random;
  private Game game;

  public ComputerPlayer(String name) {
    this(name, ThreadLocalRandom.current());
  }

  public ComputerPlayer(String name, Random random) {
    this.name = name;
    this.random = random;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int playTurn(Game game) {
    this.game = game;
    return computerMove();
  }

  public int computerMove() {
    int spot = 4;
    boolean foundSpot = false;
    while (!foundSpot) {
      if (game.board[spot] == -1) {
        game.board[spot] = game.currentPlayer;
        foundSpot = true;
      } else {
        spot = getBestMove();
        foundSpot = game.positionIsAvailable(spot);
      }
    }
    return spot;
  }

  public int getBestMove() {
    ArrayList<Integer> availableSpaces = new ArrayList<>();
    boolean foundBestMove = false;
    int spot = 100;
    for (int i = 0; i < game.board.length; i++) {
      if (game.board[i] == -1) {
        availableSpaces.add(i);
      }
    }
    for (int as : availableSpaces) {
      spot = as;
      game.board[spot] = game.currentPlayer;
      if (game.gameIsOver()) {
        game.board[spot] = -1;
        return spot;
      } else {
        game.board[spot] = game.nextPlayer();
        if (game.gameIsOver()) {
          game.board[spot] = -1;
          return spot;
        } else {
          game.board[spot] = -1;
        }
      }
    }
    if (foundBestMove) {
      return spot;
    } else {
      int n = random.nextInt(availableSpaces.size());
      return availableSpaces.get(n);
    }
  }
}
