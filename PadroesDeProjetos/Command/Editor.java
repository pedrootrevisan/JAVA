public class Editor {
    public static void main(String[] args) {
        DocumentInvoker invoker = new DocumentInvoker();


        invoker.write("This is the original text.");
        invoker.write("Here is some other text.");


        invoker.undo();


        invoker.redo();

        System.out.println(invoker.read());
    }
}