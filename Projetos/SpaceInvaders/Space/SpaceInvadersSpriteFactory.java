package bin.SpaceInvaders;

import bin.spriteframework.sprite.*;

import static bin.SpaceInvaders.CommonsSpaceInvaders.*;

public class SpaceInvadersSpriteFactory implements SpriteFactory {
    @Override
    public Player createPlayer() {
        return new Player(IMAGE_PLAYER, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    @Override
    public BomberSprite createBadSprite(int x, int y) {
        return new Alien(x, y, ALIEN_WIDTH, ALIEN_HEIGHT);
    }

    @Override
    public Shot createRay(int x, int y) {
        return new Shot(IMAGE_SHOT,x, y, SHOT_WIDTH, SHOT_HEIGHT);
    }


}