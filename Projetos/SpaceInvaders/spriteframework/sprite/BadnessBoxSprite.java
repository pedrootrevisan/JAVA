package bin.spriteframework.sprite;
import java.util.LinkedList;

public class BadnessBoxSprite extends BadSprite {
    LinkedList<BadSprite> badnesses = new LinkedList<>();

    public BadnessBoxSprite() {
    }
    public LinkedList<BadSprite> getBadnesses() {
        return this.badnesses;
    }
}
