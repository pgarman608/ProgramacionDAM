package App;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

    public boolean UP, DWON, LEFT, RIGHT, R;

    public void keyTyped(KeyEvent e){
        
    }
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            UP = true;
            LEFT = false;
            RIGHT = false;
            DWON = false;
        }
        if (code == KeyEvent.VK_S) {
            RIGHT = false;
            UP = false;
            DWON = true;
            LEFT = false;
        }
        if (code == KeyEvent.VK_A) {
            RIGHT = false;
            UP = false;
            DWON = false;
            LEFT = true;
        }
        if (code == KeyEvent.VK_D) {
            RIGHT = true;
            UP = false;
            DWON = false;
            LEFT = false;
        }
        if (code == KeyEvent.VK_0) {
            R = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            //UP = false;    
        }
        if (code == KeyEvent.VK_S) {
            //DWON = false;
        }
        if (code == KeyEvent.VK_A) {
            //LEFT = false;
        }
        if (code == KeyEvent.VK_D) {
            //RIGHT = false;
        }
        if (code == KeyEvent.VK_0) {
            R = false;
        }
    }
}
