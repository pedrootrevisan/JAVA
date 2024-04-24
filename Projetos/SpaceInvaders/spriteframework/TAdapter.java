/*A classe TAdapter é uma subclasse de KeyAdapter, que é um ouvinte de eventos de teclado.*/

package bin.spriteframework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TAdapter extends KeyAdapter {
    private AbstractBoard board;

    public TAdapter(AbstractBoard board) {
        this.board = board;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        board.players.forEach(player -> player.keyReleased(e));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        board.players.forEach(player -> {
            player.keyPressed(e);
            board.processOtherSprites(player, e);
        });
    }
}
