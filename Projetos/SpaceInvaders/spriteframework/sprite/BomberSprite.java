package bin.spriteframework.sprite;
import java.util.LinkedList;


public class BomberSprite extends BadnessBoxSprite {

    protected Bomb bomb;
    public BomberSprite(int width, int height) {
        this.width = width;
        this.height = height;
    }



    public Bomb getBomb() {
        return bomb;
    }

    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> aSlime = new LinkedList<>();
        aSlime.add(bomb);
        return aSlime;
    }
}
