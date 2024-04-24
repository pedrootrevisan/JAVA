public class TwoElement implements NumberElement{
    int a;
    int b;

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public TwoElement(int a, int b){
        this.a = a;
        this.b = b;
    }
    public void accept(NumberVisitor visitor){
        visitor.visit(this);
    }
}
