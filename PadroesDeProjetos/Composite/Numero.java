class Numero extends ExpressaoAritmetica {
    private double valor;

    public Numero(double valor) {
        this.valor = valor;
    }

    @Override
    public double getResultado() {
        return valor;
    }
}