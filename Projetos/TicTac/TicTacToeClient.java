//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class TicTacToeClient extends JFrame implements Runnable {
    private JTextField idField;
    private JTextArea displayArea;
    private JPanel boardPanel;
    private JPanel panel2;
    private Square[][] board;
    private Square currentSquare;
    private Socket connection;
    private Scanner input;
    private Formatter output;
    private String ticTacToeHost;
    private String myMark;
    private boolean myTurn;
    private final String X_MARK = "X";
    private final String O_MARK = "O";

    public TicTacToeClient(String host) {
        this.ticTacToeHost = host;
        this.displayArea = new JTextArea(4, 30);
        this.displayArea.setEditable(false);
        this.add(new JScrollPane(this.displayArea), "South");
        this.boardPanel = new JPanel();
        this.boardPanel.setLayout(new GridLayout(3, 3, 0, 0));
        this.board = new Square[3][3];

        for(int row = 0; row < this.board.length; ++row) {
            for(int column = 0; column < this.board[row].length; ++column) {
                this.board[row][column] = new Square(" ", row * 3 + column);
                this.boardPanel.add(this.board[row][column]);
            }
        }

        this.idField = new JTextField();
        this.idField.setEditable(false);
        this.add(this.idField, "North");
        this.panel2 = new JPanel();
        this.panel2.add(this.boardPanel, "Center");
        this.add(this.panel2, "Center");
        this.setSize(300, 225);
        this.setVisible(true);
        this.startClient();
    }

    public void startClient() {
        try {
            this.connection = new Socket(InetAddress.getByName(this.ticTacToeHost), 12345);
            this.input = new Scanner(this.connection.getInputStream());
            this.output = new Formatter(this.connection.getOutputStream());
        } catch (IOException var2) {
            var2.printStackTrace();
        }

        ExecutorService worker = Executors.newFixedThreadPool(1);
        worker.execute(this);
    }

    public void run() {
        this.myMark = this.input.nextLine();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicTacToeClient.this.idField.setText("You are player \"" + TicTacToeClient.this.myMark + "\"");
            }
        });
        this.myTurn = this.myMark.equals("X");

        while(true) {
            while(!this.input.hasNextLine()) {
            }

            this.processMessage(this.input.nextLine());
        }
    }

    private void processMessage(String message) {
        if (message.equals("Valid move.")) {
            this.displayMessage("Valid move, please wait.\n");
            this.setMark(this.currentSquare, this.myMark);
        } else if (message.equals("Invalid move, try again")) {
            this.displayMessage(message + "\n");
            this.myTurn = true;
        } else if (message.equals("Opponent moved")) {
            int location = this.input.nextInt();
            this.input.nextLine();
            int row = location / 3;
            int column = location % 3;
            this.setMark(this.board[row][column], this.myMark.equals("X") ? "O" : "X");
            this.displayMessage("Opponent moved. Your turn.\n");
            this.myTurn = true;
        } else {
            this.displayMessage(message + "\n");
        }

    }

    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicTacToeClient.this.displayArea.append(messageToDisplay);
            }
        });
    }

    private void setMark(final Square squareToMark, final String mark) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                squareToMark.setMark(mark);
            }
        });
    }

    public void sendClickedSquare(int location) {
        if (this.myTurn) {
            this.output.format("%d\n", location);
            this.output.flush();
            this.myTurn = false;
        }

    }

    public void setCurrentSquare(Square square) {
        this.currentSquare = square;
    }

    private class Square extends JPanel {
        private String mark;
        private int location;

        public Square(String squareMark, int squareLocation) {
            this.mark = squareMark;
            this.location = squareLocation;
            this.addMouseListener(new MouseAdapter() {
                public void mouseReleased(MouseEvent e) {
                    TicTacToeClient.this.setCurrentSquare(Square.this);
                    TicTacToeClient.this.sendClickedSquare(Square.this.getSquareLocation());
                }
            });
        }

        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }

        public Dimension getMinimumSize() {
            return this.getPreferredSize();
        }

        public void setMark(String newMark) {
            this.mark = newMark;
            this.repaint();
        }

        public int getSquareLocation() {
            return this.location;
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0, 0, 29, 29);
            g.drawString(this.mark, 11, 20);
        }
    }
}
