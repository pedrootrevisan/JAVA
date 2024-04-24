package bin.spriteframework;

import bin.spriteframework.sprite.BadSprite;
import bin.spriteframework.sprite.Player;
import bin.spriteframework.sprite.SpriteFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static bin.spriteframework.Commons.*;


public abstract class AbstractBoard extends JPanel {
    protected Dimension d;
    protected List<Player> players;
    protected ArrayList<BadSprite> badSprites;
    protected boolean inGame = true;
    protected String message = "Game Over";
    protected Timer timer;
    protected SpriteFactory spriteFactory;

    public AbstractBoard(SpriteFactory spriteFactory) {
        this.spriteFactory = spriteFactory; // Factory
        this.setSize(BOARD_WIDTH, BOARD_HEIGHT);
        this.initBoard();
        this.createPlayers();
        this.badSprites = new ArrayList<>();
        this.createBadSprites();
        this.createOtherSprites();
    }

    private void initBoard() {
        this.addKeyListener(new TAdapter(this));
        this.setFocusable(true);
        this.d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        this.setBackground(Color.green);
        this.timer = new Timer(DELAY, new GameCycle(this));
        this.timer.start();
        this.createPlayers();
        this.badSprites = new ArrayList<>();
        this.createBadSprites();
        this.createOtherSprites();
    }

    protected void createPlayers() {
        this.players = new ArrayList<>();
        this.players.add(this.spriteFactory.createPlayer()); // Factory Players
    }

    private void drawBadSprites(Graphics g) {
        for (BadSprite bad : this.badSprites) {
            if (bad.isVisible()) {
                g.drawImage(bad.getImage(), bad.getX(), bad.getY(), this);
            }

            if (bad.isDying()) {
                bad.die();
            }

            List<BadSprite> badnesses = bad.getBadnesses();
            if (badnesses != null) {
                for (BadSprite badness : badnesses) {
                    if (!badness.isDestroyed()) {
                        g.drawImage(badness.getImage(), badness.getX(), badness.getY(), this);
                    }
                }
            }
        }
    }

    private void drawPlayers(Graphics g) {
        for (Player player : this.players) {
            if (player.isVisible()) {
                g.drawImage(player.getImage(), player.getX(), player.getY(), this);
            }

            if (player.isDying()) {
                player.die();
                this.inGame = false;
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.doDrawing(g);
    }

    public void doDrawing(Graphics g1) {
        Graphics2D g = (Graphics2D) g1;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        //g.setColor(Color.decode("#66FF99"));  //cor do freezemonster
        g.fillRect(0, 0, this.d.width, this.d.height);
        if (this.inGame) {
            this.drawBadSprites(g);
            this.drawPlayers(g);
            this.drawOtherSprites(g);
        } else {
            if (this.timer.isRunning()) {
                this.timer.stop();
            }

            this.gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {
        g.setColor(new Color(0, 32, 48));
        g.fillRect(BORDER_LEFT, BOARD_HEIGHT / 2 - 50, BOARD_WIDTH, 50);
        g.setColor(Color.white);
        g.drawRect(BORDER_LEFT, BOARD_HEIGHT / 2 - 50, BOARD_WIDTH, 50);
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fontMetrics = this.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(this.message, (BOARD_WIDTH - fontMetrics.stringWidth(this.message)) / 2, BOARD_HEIGHT / 2 - 25);
    }

    protected void createBadSprites() {} //implementado nas subclasses

    protected abstract void createOtherSprites();

    protected abstract void drawOtherSprites(Graphics var1);

    protected abstract void update();

    protected abstract void processOtherSprites(Player var1, KeyEvent var2);

    public void doGameCycle() {
        this.update();
        this.repaint();
    }


}
