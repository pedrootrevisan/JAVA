package bin.spriteframework.sprite;

public interface SpriteFactory {
    Player createPlayer();
    BomberSprite createBadSprite(int x, int y);
    Shot createRay(int x, int y);

}
