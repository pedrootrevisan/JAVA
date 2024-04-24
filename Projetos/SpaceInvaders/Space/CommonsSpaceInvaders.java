package bin.SpaceInvaders;


public interface CommonsSpaceInvaders extends bin.spriteframework.Commons {

    int BOMB_HEIGHT = 5;
    int BOMB_WIDTH = 1;
    int SHOT_HEIGHT = 5;
    int SHOT_WIDTH = 1;

    String IMAGE_ALIEN ="SpaceInvaders/images/alien.png" ;
    String IMAGE_BOMB = "SpaceInvaders/images/bomb.png";
    String IMAGE_EXPLOSION ="SpaceInvaders/images/explosion.png" ;
    String IMAGE_PLAYER="SpaceInvaders/images/player.png";
    String IMAGE_SHOT = "SpaceInvaders/images/shot.png";

    int ALIEN_HEIGHT = 12;
    int ALIEN_WIDTH = 12;
    int ALIEN_INIT_X = 150;
    int ALIEN_INIT_Y = 5;
    int GO_DOWN = 15;
    int NUMBER_OF_ALIENS_TO_DESTROY = 24;
    int CHANCE = 5;
    int PLAYER_WIDTH = 15;
    int PLAYER_HEIGHT = 10;
    int GROUND = 125;
}
