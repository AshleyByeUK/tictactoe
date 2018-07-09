public class ConsoleUISpy extends ConsoleUI {

  public String callersName;

  @Override
  public int getPlayersMove(String name) {
    callersName = name;
    return 0;
  }
}
