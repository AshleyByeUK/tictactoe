import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ComputerPlayer implements Player {

  private final String token;
  private final String name;
  private Game game;

  public ComputerPlayer(String token, String name) {
    this.token = token;
    this.name = name;
  }

  @Override
  public String getToken() {
    return token;
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
    boolean foundSpot = false;
    int spot = 4;
    do {
      if (game.board[spot] == "4") {
        game.board[spot] = "O";
        foundSpot = true;
      } else {
        spot = getBestMove();
        if (game.isAvailablePosition(spot)) {
          foundSpot = true;
        } else {
          foundSpot = false;
        }
      }
    } while (!foundSpot);
    return spot;
  }

  public int getBestMove() {
    ArrayList<String> availableSpaces = new ArrayList<String>();
    boolean foundBestMove = false;
    int spot = 100;
    for (String s : game.board) {
      if (s != "X" && s != "O") {
        availableSpaces.add(s);
      }
    }
    for (String as : availableSpaces) {
      spot = Integer.parseInt(as);
      game.board[spot] = "O";
      if (game.gameIsOver()) {
        foundBestMove = true;
        game.board[spot] = as;
        return spot;
      } else {
        game.board[spot] = "X";
        if (game.gameIsOver()) {
          foundBestMove = true;
          game.board[spot] = as;
          return spot;
        } else {
          game.board[spot] = as;
        }
      }
    }
    if (foundBestMove) {
      return spot;
    } else {
      int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
      return Integer.parseInt(availableSpaces.get(n));
    }
  }
}
