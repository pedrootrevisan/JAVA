public class CalculadoraDeExpressoes {
    public static void main(String[] args) {
        ExpressaoAritmetica a = new Numero(0);
        ExpressaoAritmetica b = new Soma(new Numero(1), new Numero(2));
        ExpressaoAritmetica c = new Multiplicacao(new Numero(1), new Soma(new Numero(2), new Numero(3)));
        ExpressaoAritmetica d = new Soma(
                new Multiplicacao(new Numero(2), new Numero(3)),
                new Divisao(new Numero(4), new Subtracao(new Numero(5), new Numero(3)))
        );

        System.out.println("a) " + a.getResultado());
        System.out.println("b) " + b.getResultado());
        System.out.println("c) " + c.getResultado());
        System.out.println("d) " + d.getResultado());
    }
}