//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.HyperlinkEvent.EventType;

public class ReadServerFile extends JFrame {
    private JTextField enterField = new JTextField("Enter file URL here");
    private JEditorPane contentsArea;

    public ReadServerFile() {
        super("Simple Web Browser");
        this.enterField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ReadServerFile.this.getThePage(event.getActionCommand());
            }
        });
        this.add(this.enterField, "North");
        this.contentsArea = new JEditorPane();
        this.contentsArea.setEditable(false);
        this.contentsArea.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent event) {
                if (event.getEventType() == EventType.ACTIVATED) {
                    ReadServerFile.this.getThePage(event.getURL().toString());
                }

            }
        });
        this.add(new JScrollPane(this.contentsArea), "Center");
        this.setSize(400, 300);
        this.setVisible(true);
    }

    private void getThePage(String location) {
        try {
            this.contentsArea.setPage(location);
            this.enterField.setText(location);
        } catch (IOException var3) {
            JOptionPane.showMessageDialog(this, "Error retrieving specified URL", "Bad URL", 0);
        }

    }
}
