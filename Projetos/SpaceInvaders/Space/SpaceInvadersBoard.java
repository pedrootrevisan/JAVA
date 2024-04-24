package bin.SpaceInvaders;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Random;
import javax.swing.ImageIcon;

import bin.spriteframework.AbstractBoard;
import bin.spriteframework.sprite.*;

import static bin.SpaceInvaders.CommonsSpaceInvaders.*;

public class SpaceInvadersBoard extends AbstractBoard {
    private Shot shot;
    private int direction = -1;
    private int deaths = 0;
    private String explImg = IMAGE_EXPLOSION;

    public SpaceInvadersBoard(SpriteFactory spriteFactory) {
        super(spriteFactory);
    }

    @Override
    protected void createBadSprites() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                BomberSprite alien = spriteFactory.createBadSprite(ALIEN_INIT_X + 18 * j, ALIEN_INIT_Y + 18 * i);
                badSprites.add(alien);
            }
        }
    }

    @Override
    protected void createOtherSprites() {
        shot = new Shot();
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && inGame) {
            if (shot == null || !shot.isVisible()) {
                shot = spriteFactory.createRay(player.getX(), player.getY());
            }
        }
    }

    @Override
    protected void update() {
        updateGameStatus();
        updatePlayer();
        updateShots();
        updateAliens();
        updateBombs();
    }

    private void updateGameStatus() {
        if (deaths == NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }
    }

    private void updatePlayer() {
        players.forEach(Player::act);
    }

    private void updateShots() {
        if (shot.isVisible()) {
            moveShot();
            checkShotCollisions();
        }
    }

    private void moveShot() {
        int newY = shot.getY() - 4;
        if (newY < 0) {
            shot.die();
        } else {
            shot.setY(newY);
        }
    }

    private void checkShotCollisions() {
        int shotX = shot.getX();
        int shotY = shot.getY();
        for (BadSprite alien : badSprites) {
            if (alien.isVisible() && shot.isVisible() && shotHitsAlien(shotX, shotY, alien)) {
                ImageIcon ii = new ImageIcon(explImg);
                alien.setImage(ii.getImage());
                alien.setDying(true);
                deaths++;
                shot.die();
            }
        }
    }

    private boolean shotHitsAlien(int shotX, int shotY, BadSprite alien) {
        int alienX = alien.getX();
        int alienY = alien.getY();
        return shotX >= alienX && shotX <= (alienX + ALIEN_WIDTH) && shotY >= alienY && shotY <= (alienY + ALIEN_HEIGHT);
    }

    private void updateAliens() {
        for (BadSprite alien : badSprites) {
            if (alien.isVisible()) {
                alien.moveX(direction);
                checkAlienBounds(alien);
            }
        }
    }

    private void checkAlienBounds(BadSprite alien) {
        int x = alien.getX();
        if (x >= BOARD_WIDTH - BORDER_RIGHT && direction != -1) {
            shiftAliensDownAndReverseDirection(-1);
        } else if (x <= BORDER_LEFT && direction != 1) {
            shiftAliensDownAndReverseDirection(1);
        }
    }

    private void shiftAliensDownAndReverseDirection(int newDirection) {
        badSprites.forEach(a -> a.setY(a.getY() + GO_DOWN));
        direction = newDirection;
    }

    private void updateBombs() {
        Random generator = new Random();
        for (BadSprite alien : badSprites) {
            Bomb bomb = ((BomberSprite) alien).getBomb();
            maybeDropBomb(generator, alien, bomb);
            moveBomb(bomb);
            checkBombCollisions(bomb);
        }
    }

    private void maybeDropBomb(Random generator, BadSprite alien, Bomb bomb) {
        if (generator.nextInt(15) == CHANCE && alien.isVisible() && bomb.isDestroyed()) {
            bomb.setDestroyed(false);
            bomb.setX(alien.getX());
            bomb.setY(alien.getY());
        }
    }

    private void moveBomb(Bomb bomb) {//bomba chegue até a parte inferior da tela
        if (!bomb.isDestroyed()) {
            bomb.setY(bomb.getY() + 1);

            if (bomb.getY() >= BOARD_HEIGHT - BOMB_HEIGHT) {
                bomb.setDestroyed(true);
            }
        }
    }

    private void checkBombCollisions(Bomb bomb) {
        for (Player player : players) {
            if (player.isVisible() && !bomb.isDestroyed() && bombHitsPlayer(bomb, player)) {
                ImageIcon ii = new ImageIcon(explImg);
                player.setImage(ii.getImage());
                player.setDying(true);
                bomb.setDestroyed(true);
            }
        }
    }

    private boolean bombHitsPlayer(Bomb bomb, Player player) {
        int bombX = bomb.getX();
        int bombY = bomb.getY();
        int playerX = player.getX();
        int playerY = player.getY();
        return bombX >= playerX && bombX <= (playerX + PLAYER_WIDTH) && bombY >= playerY && bombY <= (playerY + PLAYER_HEIGHT);
    }
}
