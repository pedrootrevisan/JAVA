/*
* A classe GameCycle implementa a interface ActionListener e é usada para controlar o ciclo de jogo.
*  Essa classe é responsável por invocar repetidamente o método que atualiza o estado
*  do jogo e redesenha a tela*/

package bin.spriteframework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameCycle implements ActionListener {
    private AbstractBoard board;

    public GameCycle(AbstractBoard board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.doGameCycle();
    }
}
