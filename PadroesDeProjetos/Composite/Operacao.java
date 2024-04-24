abstract class Operacao extends ExpressaoAritmetica {
    protected ExpressaoAritmetica op1;
    protected ExpressaoAritmetica op2;

    public Operacao(ExpressaoAritmetica op1, ExpressaoAritmetica op2) {
        this.op1 = op1;
        this.op2 = op2;
    }
}