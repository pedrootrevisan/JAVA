import java.util.ArrayList;
import java.util.List;

//exemplo de visitor da aula para incluir
// novos visitor de Multiplicação e de Soma Total dos Elementos Multiplicados,
// similares aos existentes.

public class Demo3 {
    public static void main(String[] args) {
        TwoElement two1 = new TwoElement(3, 3);
        TwoElement two2 = new TwoElement(2, 7);
        ThreeElement three1 = new ThreeElement(3, 4, 5);

        List<NumberElement> numberElements = new ArrayList<>();
        numberElements.add(two1);
        numberElements.add(two2);
        numberElements.add(three1);

        SumVisitor sumVisitor = new SumVisitor();
        System.out.println("Visiting with SumVisitor:");
        visitAllElements(numberElements, sumVisitor);

        ProductVisitor productVisitor = new ProductVisitor();
        System.out.println("\nVisiting with ProductVisitor:");
        visitAllElements(numberElements, productVisitor);

        TotalProductVisitor totalProductVisitor = new TotalProductVisitor();
        System.out.println("\nVisiting with TotalProductVisitor:");
        visitAllElements(numberElements, totalProductVisitor);
        System.out.println("Total product: " + totalProductVisitor.getTotalProduct());
    }

    private static void visitAllElements(List<NumberElement> elements, NumberVisitor visitor) {
        for (NumberElement elem : elements) {
            elem.accept(visitor);
        }
    }
}
