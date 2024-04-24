

package bin.spriteframework;

import bin.spriteframework.sprite.SpriteFactory;

import javax.swing.*;

import static bin.spriteframework.Commons.*;


public abstract class MainFrame extends JFrame {
    protected abstract AbstractBoard createBoard(SpriteFactory spriteFactory);

    public MainFrame(String t, SpriteFactory spriteFactory) {
        this.add(this.createBoard(spriteFactory));
        this.setTitle(t);
        this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
