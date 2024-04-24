package bin.spriteframework.sprite;

import bin.ImageResizer;

import javax.swing.*;

public class Bomb extends BadSprite {
    private boolean destroyed;

    public Bomb(String path, int x, int y, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
        initializeBomb(x, y);
    }

    private void initializeBomb(int x, int y) {
        setDestroyed(true);

        this.x = x;
        this.y = y;

        ImageIcon resizedIcon = ImageResizer.resizeImage(path,  width, height);
        setImage(resizedIcon.getImage());
    }

    public void setDestroyed(boolean isDestroyed) {
        destroyed = isDestroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
