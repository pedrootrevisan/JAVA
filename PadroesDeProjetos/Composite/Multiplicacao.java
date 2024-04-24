class Multiplicacao extends Operacao {
    public Multiplicacao(ExpressaoAritmetica op1, ExpressaoAritmetica op2) {
        super(op1, op2);
    }

    @Override
    public double getResultado() {
        return op1.getResultado() * op2.getResultado();
    }
}