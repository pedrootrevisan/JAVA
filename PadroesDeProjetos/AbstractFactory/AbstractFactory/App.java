package main.java.abstractFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Logger;

public class App implements Runnable {


  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
  private final Kingdom kingdom = new Kingdom();

  public static void main(String[] args) {
    var app = new App();
    app.run();
  }

  @Override
  public void run() {
    LOGGER.info("elf kingdom");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ELF);
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());

    LOGGER.info("orc kingdom");
    createKingdom(Kingdom.FactoryMaker.KingdomType.ORC);
    LOGGER.info(kingdom.getArmy().getDescription());
    LOGGER.info(kingdom.getCastle().getDescription());
    LOGGER.info(kingdom.getKing().getDescription());
  }

  public void createKingdom(final Kingdom.FactoryMaker.KingdomType kingdomType) {
    final KingdomFactory kingdomFactory = Kingdom.FactoryMaker.makeFactory(kingdomType);
    kingdom.setKing(kingdomFactory.createKing());
    kingdom.setCastle(kingdomFactory.createCastle());
    kingdom.setArmy(kingdomFactory.createArmy());
  }

  public Kingdom getKingdom() {
    return kingdom;
  }
}
