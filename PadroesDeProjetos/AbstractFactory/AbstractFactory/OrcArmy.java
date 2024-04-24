package main.java.abstractFactory;

public class OrcArmy implements Army {

  static final String DESCRIPTION = "This is the orc army!";

  @Override
  public static String getDescription() {
    return DESCRIPTION;
  }
}
