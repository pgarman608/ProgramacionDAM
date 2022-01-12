/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patata;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Teclado implements KeyListener{
    public boolean UP,DOWN,LEFT,RIGHT;
    
    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            UP = true;
        }
        if (code == KeyEvent.VK_S) {
            DOWN = true;
        }
        if (code == KeyEvent.VK_A) {
            LEFT = true;
        }
        if (code == KeyEvent.VK_D) {
            RIGHT = true;
        }
    }

    public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            UP = false;    
        }
        if (code == KeyEvent.VK_S) {
            DOWN = false;
        }
        if (code == KeyEvent.VK_A) {
            LEFT = false;
        }
        if (code == KeyEvent.VK_D) {
            RIGHT = false;
        }
    }
}
