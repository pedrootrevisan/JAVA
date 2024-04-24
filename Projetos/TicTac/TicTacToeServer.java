//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TicTacToeServer extends JFrame {
    private String[] board = new String[9];
    private JTextArea outputArea;
    private Player[] players;
    private ServerSocket server;
    private int currentPlayer;
    private static final int PLAYER_X = 0;
    private static final int PLAYER_O = 1;
    private static final String[] MARKS = new String[]{"X", "O"};
    private ExecutorService runGame = Executors.newFixedThreadPool(2);
    private Lock gameLock = new ReentrantLock();
    private Condition otherPlayerConnected;
    private Condition otherPlayerTurn;

    // Matriz de combinações vencedoras
    private static final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Linhas
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Colunas
            {0, 4, 8}, {2, 4, 6}             // Diagonais
    };

    // Declaração das constantes para conexão com o banco de dados
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tictactoe";
    private static final String DB_USER = "username";
    private static final String DB_PASSWORD = "password";

    public TicTacToeServer() {
        super("Tic-Tac-Toe Server");
        this.otherPlayerConnected = this.gameLock.newCondition();
        this.otherPlayerTurn = this.gameLock.newCondition();

        for (int i = 0; i < 9; ++i) {
            this.board[i] = new String("");
        }

        this.players = new Player[2];
        this.currentPlayer = 0;

        try {
            this.server = new ServerSocket(12345, 2);
        } catch (IOException var2) {
            var2.printStackTrace();
            System.exit(1);
        }

        this.outputArea = new JTextArea();
        this.add(this.outputArea, "Center");
        this.outputArea.setText("Server awaiting connections\n");
        this.setSize(300, 300);
        this.setVisible(true);
    }

    public void execute() {
        for (int i = 0; i < this.players.length; ++i) {
            try {
                this.players[i] = new Player(this.server.accept(), i);
                this.runGame.execute(this.players[i]);
            } catch (IOException var6) {
                var6.printStackTrace();
                System.exit(1);
            }
        }

        this.gameLock.lock();

        try {
            this.players[0].setSuspended(false);
            this.otherPlayerConnected.signal();
        } finally {
            this.gameLock.unlock();
        }

    }

    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TicTacToeServer.this.outputArea.append(messageToDisplay);
            }
        });
    }

    public boolean validateAndMove(int location, int player) {
        while (player != this.currentPlayer) {
            this.gameLock.lock();

            try {
                this.otherPlayerTurn.await();
            } catch (InterruptedException var12) {
                var12.printStackTrace();
            } finally {
                this.gameLock.unlock();
            }
        }

        if (!this.isOccupied(location)) {
            this.board[location] = MARKS[this.currentPlayer];
            this.currentPlayer = (this.currentPlayer + 1) % 2;

            // Verifica se o jogador venceu após a jogada
            if (checkForWin(MARKS[player])) {
                announceWinner(MARKS[player]);
            } else {
                this.players[this.currentPlayer].otherPlayerMoved(location);
                this.gameLock.lock();

                try {
                    this.otherPlayerTurn.signal();
                } finally {
                    this.gameLock.unlock();
                }
            }

            return true;
        } else {
            return false;
        }
    }

    // Verifica se há um vencedor
    public boolean checkForWin(String mark) {
        for (int i = 0; i < WINNING_COMBINATIONS.length; i++) {
            if (board[WINNING_COMBINATIONS[i][0]].equals(mark) &&
                    board[WINNING_COMBINATIONS[i][1]].equals(mark) &&
                    board[WINNING_COMBINATIONS[i][2]].equals(mark)) {
                return true;
            }
        }
        return false;
    }

    // Verifica se o jogo terminou em empate
    public boolean checkForGameOver() {
        for (String cell : board) {
            if (cell.equals("")) {
                return false;
            }
        }
        return true;
    }

    // Anuncia o vencedor
    public void announceWinner(String winnerMark) {
        displayMessage("Player " + winnerMark + " wins!\n");
    }

    public boolean isOccupied(int location) {
        return this.board[location].equals(MARKS[0]) || this.board[location].equals(MARKS[1]);
    }

    public boolean isGameOver() {
        return false;
    }

    // Método para salvar o estado do jogo no banco de dados
    public void saveGameState(String gameId, String playerId, String boardState, String gameStatus) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO Game VALUES (?, ?, ?, ?)")) {

            statement.setString(1, gameId);
            statement.setString(2, playerId);
            statement.setString(3, boardState);
            statement.setString(4, gameStatus);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar o estado do jogo do banco de dados
    public String[] loadGameState(String gameId) {
        String[] gameState = new String[3];
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Game WHERE game_id = ?")) {

            statement.setString(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                gameState[0] = resultSet.getString("player_id");
                gameState[1] = resultSet.getString("board_state");
                gameState[2] = resultSet.getString("game_status");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gameState;
    }

    private class Player implements Runnable {
        private Socket connection;
        private Scanner input;
        private Formatter output;
        private int playerNumber;
        private String mark;
        private boolean suspended = true;

        public Player(Socket socket, int number) {
            this.playerNumber = number;
            this.mark = TicTacToeServer.MARKS[this.playerNumber];
            this.connection = socket;

            try {
                this.input = new Scanner(this.connection.getInputStream());
                this.output = new Formatter(this.connection.getOutputStream());
            } catch (IOException var5) {
                var5.printStackTrace();
                System.exit(1);
            }

        }

        public void otherPlayerMoved(int location) {
            this.output.format("Opponent moved\n");
            this.output.format("%d\n", location);
            this.output.flush();
        }

        public void run() {
            try {
                TicTacToeServer.this.displayMessage("Player " + this.mark + " connected\n");
                this.output.format("%s\n", this.mark);
                this.output.flush();
                if (this.playerNumber != 0) {
                    this.output.format("Player O connected, please wait\n");
                    this.output.flush();
                } else {
                    this.output.format("%s\n%s", "Player X connected", "Waiting for another player\n");
                    this.output.flush();
                    TicTacToeServer.this.gameLock.lock();

                    while (true) {
                        try {
                            if (this.suspended) {
                                TicTacToeServer.this.otherPlayerConnected.await();
                                continue;
                            }
                        } catch (InterruptedException var15) {
                            var15.printStackTrace();
                        } finally {
                            TicTacToeServer.this.gameLock.unlock();
                        }

                        this.output.format("Other player connected. Your move.\n");
                        this.output.flush();
                        break;
                    }
                }

                while (!TicTacToeServer.this.isGameOver()) {
                    int location = 0;
                    if (this.input.hasNext()) {
                        location = this.input.nextInt();
                    }

                    if (TicTacToeServer.this.validateAndMove(location, this.playerNumber)) {
                        TicTacToeServer.this.displayMessage("\nlocation: " + location);
                        this.output.format("Valid move.\n");
                        this.output.flush();
                    } else {
                        this.output.format("Invalid move, try again\n");
                        this.output.flush();
                    }
                }
            } finally {
                try {
                    this.connection.close();
                } catch (IOException var14) {
                    var14.printStackTrace();
                    System.exit(1);
                }

            }

        }

        public void setSuspended(boolean status) {
            this.suspended = status;
        }
    }
}