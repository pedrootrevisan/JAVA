//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class TicTacToeClientTest {
    public TicTacToeClientTest() {
    }

    public static void main(String[] args) {
        TicTacToeClient application;
        if (args.length == 0) {
            application = new TicTacToeClient("127.0.0.1");
        } else {
            application = new TicTacToeClient(args[0]);
        }

        application.setDefaultCloseOperation(3);
    }
}
