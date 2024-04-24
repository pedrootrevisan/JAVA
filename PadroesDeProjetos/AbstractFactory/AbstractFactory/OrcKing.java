package main.java.abstractFactory;

public class OrcKing implements King {

  static final String DESCRIPTION = "This is the orc king!";

  @Override
  public static String getDescription() {
    return DESCRIPTION;
  }
}
