package main.java.abstractFactory;

public class DwarfKingdomFactory implements KingdomFactory {
    @Override
    public Castle createCastle() {
        return new DwarfCastle();
    }

    @Override
    public King createKing() {
        return new DwarfKing();
    }

    @Override
    public Army createArmy() {
        return new DwarfArmy();
    }

    @Override
    public Wizard createWizard() {
        return new DwarfWizard();
    }
}
