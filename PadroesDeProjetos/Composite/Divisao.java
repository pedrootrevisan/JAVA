class Divisao extends Operacao {
    public Divisao(ExpressaoAritmetica op1, ExpressaoAritmetica op2) {
        super(op1, op2);
    }

    @Override
    public double getResultado() {
        double divisor = op2.getResultado();
        if (divisor == 0) {
            throw new ArithmeticException("Divisão por zero.");
        }
        return op1.getResultado() / divisor;
    }
}
