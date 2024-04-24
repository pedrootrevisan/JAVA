package main.java.abstractFactory;

public class Kingdom {

  private King king;
  private Castle castle;
  private Army army;

  public King getKing() {
    return king;
  }

  public void setKing(King king) {
    this.king = king;
  }

  public Castle getCastle() {
    return castle;
  }

  public void setCastle(Castle castle) {
    this.castle = castle;
  }

  public Army getArmy() {
    return army;
  }

  public void setArmy(Army army) {
    this.army = army;
  }

  /**
   * The factory of kingdom factories.
   */
  public static class FactoryMaker {

    /**
     * Enumeration for the different types of Kingdoms.
     */
    public enum KingdomType {
      ELF, ORC
    }

    /**
     * The factory method to create KingdomFactory concrete objects.
     */
    public static KingdomFactory makeFactory(KingdomType type) {
      return switch (type) {
        case ELF -> new ElfKingdomFactory();
        case ORC -> new OrcKingdomFactory();
      };
    }
  }
}
