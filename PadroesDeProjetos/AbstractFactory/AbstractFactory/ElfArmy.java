package main.java.abstractFactory;

public class ElfArmy implements Army {

  static final String DESCRIPTION = "This is the elven army!";

  @Override
  public static String getDescription() {
    return DESCRIPTION;
  }
}
