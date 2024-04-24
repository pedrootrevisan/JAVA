package main.java.abstractFactory;

public class ElfCastle implements Castle {

  static final String DESCRIPTION = "This is the elven castle!";

  @Override
  public static String getDescription() {
    return DESCRIPTION;
  }
}
