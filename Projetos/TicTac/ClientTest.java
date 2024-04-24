//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class ClientTest {
    public ClientTest() {
    }

    public static void main(String[] args) {
        Client application;
        if (args.length == 0) {
            application = new Client("127.0.0.1");
        } else {
            application = new Client(args[0]);
        }

        application.setDefaultCloseOperation(3);
        application.runClient();
    }
}
