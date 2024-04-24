//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ClientDatagram extends JFrame {
    private JTextField enterField = new JTextField("Type message here");
    private JTextArea displayArea;
    private DatagramSocket socket;

    public ClientDatagram() {
        super("Client");
        this.enterField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String message = event.getActionCommand();
                    ClientDatagram.this.displayArea.append("\nSending packet containing: " + message + "\n");
                    byte[] data = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5000);
                    ClientDatagram.this.socket.send(sendPacket);
                    ClientDatagram.this.displayArea.append("Packet sent\n");
                    ClientDatagram.this.displayArea.setCaretPosition(ClientDatagram.this.displayArea.getText().length());
                } catch (IOException var5) {
                    ClientDatagram.this.displayMessage(var5 + "\n");
                    var5.printStackTrace();
                }

            }
        });
        this.add(this.enterField, "North");
        this.displayArea = new JTextArea();
        this.add(new JScrollPane(this.displayArea), "Center");
        this.setSize(400, 300);
        this.setVisible(true);

        try {
            this.socket = new DatagramSocket();
        } catch (SocketException var2) {
            var2.printStackTrace();
            System.exit(1);
        }

    }

    public void waitForPackets() {
        while(true) {
            try {
                byte[] data = new byte[100];
                DatagramPacket receivePacket = new DatagramPacket(data, data.length);
                this.socket.receive(receivePacket);
                this.displayMessage("\nPacket received:\nFrom host: " + receivePacket.getAddress() + "\nHost port: " + receivePacket.getPort() + "\nLength: " + receivePacket.getLength() + "\nContaining:\n\t" + new String(receivePacket.getData(), 0, receivePacket.getLength()));
            } catch (IOException var3) {
                this.displayMessage(var3 + "\n");
                var3.printStackTrace();
            }
        }
    }

    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClientDatagram.this.displayArea.append(messageToDisplay);
            }
        });
    }
}
