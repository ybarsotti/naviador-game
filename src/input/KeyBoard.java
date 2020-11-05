package input;

import Scene.Cena;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;
import game.Game;

/**
 * @author Yuri Barsotti
 */
public class KeyBoard implements KeyListener {
    private Cena cena;

    public KeyBoard(Cena cena) {
        this.cena = cena;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                cena.game.controller.ship.up();
                break;
            case KeyEvent.VK_DOWN:
                cena.game.controller.ship.down();
                break;
            case KeyEvent.VK_RIGHT:
                cena.game.controller.ship.right();
                break;
            case KeyEvent.VK_LEFT:
                cena.game.controller.ship.left();
                break;
            case KeyEvent.VK_SPACE:
                cena.game.controller.ship.shoot();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
