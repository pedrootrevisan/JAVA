import java.util.ArrayList;
import java.util.List;

// exemplo adaptado sem padrão visitor usa as classes ThreeElement2 e twoElement2

public class Demo2 {

    public static void main(String[] args) {
        TwoElement2 two1 = new TwoElement2(3, 3);
        TwoElement2 two2 = new TwoElement2(2, 7);
        ThreeElement three1 = new ThreeElement(3, 4, 5);

        List<Object> elements = new ArrayList<>();
        elements.add(two1);
        elements.add(two2);
        elements.add(three1);

        int totalSum = 0;
        int totalProduct = 1;

        for (Object element : elements) {
            if (element instanceof TwoElement2) {
                totalSum += ((TwoElement2) element).sum();
                totalProduct *= ((TwoElement2) element).product();
            } else if (element instanceof ThreeElement2) {
                totalSum += ((ThreeElement2) element).sum();
                totalProduct *= ((ThreeElement2) element).product();
            }
        }

        System.out.println("Total sum: " + totalSum);
        System.out.println("Total product: " + totalProduct);
    }
}