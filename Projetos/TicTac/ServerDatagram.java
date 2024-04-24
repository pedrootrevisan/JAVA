//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class ServerDatagram extends JFrame {
    private JTextArea displayArea = new JTextArea();
    private DatagramSocket socket;

    public ServerDatagram() {
        super("Server");
        this.add(new JScrollPane(this.displayArea), "Center");
        this.setSize(400, 300);
        this.setVisible(true);

        try {
            this.socket = new DatagramSocket(5000);
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
                this.sendPacketToClient(receivePacket);
            } catch (IOException var3) {
                this.displayMessage(var3 + "\n");
                var3.printStackTrace();
            }
        }
    }

    private void sendPacketToClient(DatagramPacket receivePacket) throws IOException {
        this.displayMessage("\n\nEcho data to client...");
        DatagramPacket sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(), receivePacket.getAddress(), receivePacket.getPort());
        this.socket.send(sendPacket);
        this.displayMessage("Packet sent\n");
    }

    private void displayMessage(final String messageToDisplay) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ServerDatagram.this.displayArea.append(messageToDisplay);
            }
        });
    }
}
