package main.java.abstractFactory;

public interface KingdomFactory {

  Castle createCastle();

  King createKing();

  Army createArmy();

  Wizard createWizard();
}
