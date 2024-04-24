import java.util.List;

public class TotalProductVisitor implements NumberVisitor {

    private int totalProduct = 1;

    public int getTotalProduct() {
        return totalProduct;
    }

    @Override
    public void visit(TwoElement twoElement) {
        totalProduct *= twoElement.getA() * twoElement.getB();
        System.out.println("Visiting TwoElement: Multiplying to totalProduct, new value is " + totalProduct);
    }

    @Override
    public void visit(ThreeElement threeElement) {
        totalProduct *= threeElement.getA() * threeElement.getB() * threeElement.getC();
        System.out.println("Visiting ThreeElement: Multiplying to totalProduct, new value is " + totalProduct);
    }

    @Override
    public void visit(List<NumberElement> elementList) {
        for (NumberElement element : elementList) {
            element.accept(this);
        }
    }
}
