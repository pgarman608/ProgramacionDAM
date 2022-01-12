/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patata;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.Timer;
import ;
public class Game extends JPanel implements Runnable{
    AnimationTimer tim = new AnimationTimer();
    final int FPS = 60;
    Timer time = new Timer();
    Thread patataThread;
    Teclado teclado = new Teclado();
    final int wight = 600;
    final int height = 400;
    Thread pt2;
    public Game() {
        this.setPreferredSize(new Dimension(600,400));
        this.setMaximumSize(new Dimension(600,400));
        this.setMinimumSize(new Dimension(600,400));
        
        this.setDoubleBuffered(true);
        this.addKeyListener(teclado);
        this.setFocusable(true);
    }
    public void SP(){
        patataThread = new Thread(this);
        patataThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long current;
        long timer= 0;
        int drawCount=0;
        while(patataThread != null){
            current = System.nanoTime();
            delta += (current - lastTime)/drawInterval;
            timer += (current-lastTime);
            lastTime = current;
            if (delta>= 1) {
                update();
                repaint();
                delta-=1;
                drawCount++;
            }
            if (timer>= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }
    int jugadorX = 30;
    int jugadorY = height-130;
    private void update() {
        if (teclado.UP == true) {
            altc = 64;
            for (int i = 0; i < 100; i++) {
                if (i/5 ==0) {
                    
                    jugadorY -=1;
                    
                }
            }
            if (teclado.UP== false) {
                for (int i = 0; i < 100; i++) {
                    if (i/5 ==0) {
                        jugadorY +=1;
                    }
                }
            }
        }
        if (teclado.DOWN == true) {
            jugadorY =height -98;
            altc = 32;
        }
        if (teclado.DOWN == false) {
            jugadorY =height -130;
            altc = 64;
        }
        
        if (teclado.LEFT == true) {
            
        }
        if (teclado.RIGHT == true) {
            
        }
    }
    int altc=64;
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);
        g2.fillRect(32,jugadorY, 64, altc);
        g2.setStroke(new java.awt.BasicStroke(6));
        g2.setColor(Color.GREEN);
        g2.drawLine(0, height-64, wight, height-64);
        g2.dispose();
    }
}
