package test.java.test.abstractFactory;



import main.java.abstractFactory.App;
import main.java.abstractFactory.Kingdom;
import main.java.abstractFactory.ElfKing;
import main.java.abstractFactory.OrcKing;
import main.java.abstractFactory.ElfCastle;
import main.java.abstractFactory.OrcCastle;
import main.java.abstractFactory.ElfArmy;
import main.java.abstractFactory.OrcArmy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for abstract factory.
 */
class AbstractFactoryTest {

  private final App app = new App();

  @Test
  void verifyKingCreation() {
    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    final var kingdom = app.getKingdom();

    final var elfKing = kingdom.getKing();
    assertTrue(elfKing instanceof ElfKing);
    assertEquals(ElfKing.getDescription(), elfKing.getDescription());

    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    final var orcKing = kingdom.getKing();
    assertTrue(orcKing instanceof OrcKing);
    assertEquals(OrcKing.getDescription(), orcKing.getDescription());
  }

  @Test
  void verifyCastleCreation() {
    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    final var kingdom = app.getKingdom();

    final var elfCastle = kingdom.getCastle();
    assertTrue(elfCastle instanceof ElfCastle);
    assertEquals(ElfCastle.getDescription(), elfCastle.getDescription());

    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    final var orcCastle = kingdom.getCastle();
    assertTrue(orcCastle instanceof OrcCastle);
    assertEquals(OrcCastle.getDescription(), orcCastle.getDescription());
  }

  @Test
  void verifyArmyCreation() {
    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    final var kingdom = app.getKingdom();

    final var elfArmy = kingdom.getArmy();
    assertTrue(elfArmy instanceof ElfArmy);
    assertEquals(ElfArmy.getDescription(), elfArmy.getDescription());

    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    final var orcArmy = kingdom.getArmy();
    assertTrue(orcArmy instanceof OrcArmy);
    assertEquals(OrcArmy.getDescription(), orcArmy.getDescription());
  }

  @Test
  void verifyElfKingdomCreation() {
    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    final var kingdom = app.getKingdom();

    final var king = kingdom.getKing();
    final var castle = kingdom.getCastle();
    final var army = kingdom.getArmy();
    assertTrue(king instanceof ElfKing);
    assertEquals(ElfKing.getDescription(), king.getDescription());
    assertTrue(castle instanceof ElfCastle);
    assertEquals(ElfCastle.getDescription(), castle.getDescription());
    assertTrue(army instanceof ElfArmy);
    assertEquals(ElfArmy.getDescription(), army.getDescription());
  }

  @Test
  void verifyOrcKingdomCreation() {
    app.createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    final var kingdom = app.getKingdom();

    final var king = kingdom.getKing();
    final var castle = kingdom.getCastle();
    final var army = kingdom.getArmy();
    assertTrue(king instanceof OrcKing);
    assertEquals(OrcKing.getDescription(), king.getDescription());
    assertTrue(castle instanceof OrcCastle);
    assertEquals(OrcCastle.getDescription(), castle.getDescription());
    assertTrue(army instanceof OrcArmy);
    assertEquals(OrcArmy.getDescription(), army.getDescription());
  }
}
