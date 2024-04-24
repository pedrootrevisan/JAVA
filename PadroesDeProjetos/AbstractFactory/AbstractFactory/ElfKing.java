package main.java.abstractFactory;

public class ElfKing implements King {

  static final String DESCRIPTION = "This is the elven king!";

  @Override
  public static String getDescription() {
    return DESCRIPTION;
  }
}
