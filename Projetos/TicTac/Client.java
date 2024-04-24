//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame {
    private JTextField enterField;
    private JTextArea displayArea;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message = "";
    private String chatServer;
    private Socket client;

    public Client(String host) {
        super("Client");
        this.chatServer = host;
        this.enterField = new JTextField();
        this.enterField.setEditable(false);
        this.enterField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Client.this.sendData(event.getActionCommand());
                Client.this.enterField.setText("");
            }
        });
        this.add(this.enterField, "North");
        this.displayArea = new JTextArea();
        this.add(new JScrollPane(this.displayArea), "Center");
        this.setSize(300, 150);
        this.setVisible(true);
    }

    public void runClient() {
        try {
            this.connectToServer();
            this.getStreams();
            this.processConnection();
        } catch (EOFException var6) {
            this.displayMessage("\nClient terminated connection");
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            this.closeConnection();
        }

    }

    private void connectToServer() throws IOException {
        this.displayMessage("Attempting connection\n");
        this.client = new Socket(InetAddress.getByName(this.chatServer), 12345);
        this.displayMessage("Connected to: " + this.client.getInetAddress().getHostName());
    }

    private void getStreams() throws IOException {
        this.output = new ObjectOutputStream(this.client.getOutputStream());
        this.output.flush();
        this.input = new ObjectInputStream(this.client.getInputStream());
        this.displayMessage("\nGot I/O streams\n");
    }

    private void processConnection() throws IOException {
        this.setTextFieldEditable(true);

        do {
            try {
                this.message = (String)this.input.readObject();
                this.displayMessage("\n" + this.message);
            } catch (ClassNotFoundException var2) {
                this.displayMessage("\nUnknown object type received");
            }
        } while(!this.message.equals("SERVER>>> TERMINATE"));

    }

    private void closeConnection() {
        this.displayMessage("\nClosing connection");
        this.setTextFieldEditable(false);

        try {
            this.output.close();
            this.input.close();
            this.client.close();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    private void sendData(String message) {
        try {
            this.output.writeObject("CLIENT>>> " + message);
            this.output.flush();
            this.displayMessage("\nCLIENT>>> " + message);
        } catch (IOException var3) {
            this.displayArea.append("\nError writing object");
        }

    }

    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Client.this.displayArea.append(messageToDisplay);
            }
        });
    }

    private void setTextFieldEditable(final boolean editable) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Client.this.enterField.setEditable(editable);
            }
        });
    }
}
