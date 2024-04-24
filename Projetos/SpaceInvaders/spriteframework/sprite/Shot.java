package bin.spriteframework.sprite;

import bin.ImageResizer;

import javax.swing.ImageIcon;


public class Shot extends BadSprite {
    public Shot() {
        setVisible(false);
    }

    public Shot(String path, int x, int y, int width, int height) {
        this.path = path;
        this.width = width;
        this.height = height;
        initializeRay(x, y);
    }

    private void initializeRay(int x, int y) {
        ImageIcon resizedIcon = ImageResizer.resizeImage(path, width, height);
        setImage(resizedIcon.getImage());
        setVisible(true);
        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
