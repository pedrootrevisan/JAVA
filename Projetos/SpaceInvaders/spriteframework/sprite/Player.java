package bin.spriteframework.sprite;

import bin.ImageResizer;


import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

import static bin.spriteframework.Commons.*;


public class Player extends Sprite {
    private int width;
    private int height;
    private final String path;


    public Player(String path, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
        loadImage();
        resetState();
        setDirection(0, -1);
    }

    protected void loadImage() {

        ImageIcon resizedIcon = ImageResizer.resizeImage(path, width, height);
        width = resizedIcon.getIconWidth();
        height = resizedIcon.getIconHeight();
        setImage(resizedIcon.getImage());
    }

    public void act() {
        x += dx;
        y += dy;
        constrainToBoardBounds();
    }

    private void constrainToBoardBounds() {
        x = Math.max(2, Math.min(BOARD_WIDTH - 2 * width, x));
        y = Math.max(2, Math.min(BOARD_HEIGHT - 2 * height, y));
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -PLAYER_SPEED;
            setDirection(-1, 0);
        } else if (key == KeyEvent.VK_RIGHT) {
            dx = PLAYER_SPEED;
            setDirection(1, 0);
        } else if (key == KeyEvent.VK_UP) {
            dy = -PLAYER_SPEED;
            setDirection(0, -1);
        } else if (key == KeyEvent.VK_DOWN) {
            dy = PLAYER_SPEED;
            setDirection(0, 1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    private void resetState() {
        setX(INIT_PLAYER_X);
        setY(INIT_PLAYER_Y);
    }
}
