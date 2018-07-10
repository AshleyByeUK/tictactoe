public class ConsoleUISpy extends ConsoleUI {

  public String callersName;
  public String lastMessage;
  private int timesCalled;
  private String input;

  @Override
  public String getInputForPlayer(String name, int maxOption) {
    callersName = name;
    return mockUserInput();
  }

  private String mockUserInput() {
    timesCalled++;
    if (timesCalled > 1)
      return "0";
    return input;
  }

  public void setUserInputValue(String input) {
    this.input = input;
  }

  @Override
  public void sendMessage(String message) {
    lastMessage = message;
  }
}
