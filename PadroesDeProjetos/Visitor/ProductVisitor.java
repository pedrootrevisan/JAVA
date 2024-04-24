import java.util.List;
public class ProductVisitor implements NumberVisitor {

    @Override
    public void visit(TwoElement twoElement) {
        int product = twoElement.getA() * twoElement.getB();
        System.out.println("Visiting TwoElement: " + twoElement.getA() + " * " + twoElement.getB() + " = " + product);
    }

    @Override
    public void visit(ThreeElement threeElement) {
        int product = threeElement.getA() * threeElement.getB() * threeElement.getC();
        System.out.println("Visiting ThreeElement: " + threeElement.getA() + " * " + threeElement.getB() + " * " + threeElement.getC() + " = " + product);
    }

    @Override
    public void visit(List<NumberElement> elementList) {
        for (NumberElement element : elementList) {
            element.accept(this);
        }
    }
}
