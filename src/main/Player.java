public interface Player {

  String getToken();

  String getName();

  int playTurn(Game game);
}
