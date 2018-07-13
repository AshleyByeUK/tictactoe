package console;

public interface GamePlayView {

  void setPlayerOneToken(String token);

  void setPlayerTwoToken(String token);

  void show(GamePlayViewModel viewModel);
}
