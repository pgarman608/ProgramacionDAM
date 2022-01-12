package App;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class AppPanel extends JPanel implements Runnable{
    //SCRENN SETTINGS
    final int originalTileSize = 32;
    final int scale = 2;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 14;
    final int maxScreenRow = 10;
    final int screenWidth = tileSize * maxScreenCol; //1152 px
    final int screenheight = tileSize * maxScreenRow; //768 px
    Teclado teclado = new Teclado();
    Thread appThread;
    int jugadorX = 1;
    int jugadorY = 1;
    int speedx = 1;
    int speedy = 1;
    int ratonx=0;
    int ratony=0;
    int cuerpox=jugadorX;
    int cuerpoy=jugadorY;
    int numcuerpo = 1;
    int contrat= 1;

    //FPS
    int FPS = 60;
    //Imagenes
    private BufferedImage img;
    private BufferedImage GROUND1;
    private BufferedImage GROUND2;
    private BufferedImage Serp1;
    private BufferedImage Serp0 = Serp1;
    private BufferedImage Serp2;
    private BufferedImage Serp3;
    private BufferedImage Serp4;
    private BufferedImage mouse;
    private int[][] Generatorfood = new int[maxScreenCol][maxScreenRow];

    public AppPanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenheight));
        this.setMaximumSize(new Dimension(screenWidth,screenheight));
        this.setMinimumSize(new Dimension(screenWidth,screenheight));
        
        this.setDoubleBuffered(true);
        this.addKeyListener(teclado);
        this.setFocusable(true);
    }

    public void startApp(){
        appThread = new Thread(this);
        appThread.start();
    }
    
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long current;
        long timer= 0;
        int drawCount=0;
        while(appThread != null){
            current = System.nanoTime();
            delta += (current - lastTime)/drawInterval;
            timer += (current-lastTime);
            lastTime = current;
            if (delta>= 12) {
                update();
                repaint();
                delta-=12;
                drawCount++;
            }
            if (timer>= 1000000000) {
                System.out.println("FPS: " + drawCount + " JugadorX= " + jugadorX + " JugadorY= "+ jugadorY);
                drawCount=0;
                timer=0;
            }
        }
    }
    public void update() {
        if (teclado.UP == true) {
            Serp0 = Serp3;
            jugadorY -= 1;
        }
        if (teclado.DWON == true) {
            Serp0 = Serp4;
            jugadorY += 1;
        }
        if (teclado.LEFT == true) {
            Serp0 = Serp2;
            jugadorX -= 1;
        }
        if (teclado.RIGHT == true) {
            Serp0 = Serp1;
            jugadorX += 1;
        }
        if (jugadorY <=-1) {
            jugadorY =0;
        }
        if (jugadorX <=-1) {
            jugadorX =0;
        }
        if (jugadorX >= maxScreenCol) {
            jugadorX = maxScreenCol-1;
        }
        if (jugadorY >= maxScreenRow) {
            jugadorY = maxScreenRow-1;
        }
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        GetBack(g2);
        int aux =0;
        for (int i = 0; i < maxScreenCol; i++) {
            for (int j = 0; j < maxScreenRow; j++) {
                if (aux % 2 ==0) {
                    if (j % 2 == 0) {
                        g2.drawImage(GROUND1, null, i*64, j*64);
                    }else{
                        g2.drawImage(GROUND2, null, i*64, j*64);
                    }
                } else {
                    if (j % 2 == 0) {
                        g2.drawImage(GROUND2, null, i*64, j*64);
                    }else{
                        g2.drawImage(GROUND1, null, i*64, j*64);
                    }
                }
            }
            aux++;
        }
        g2.setStroke(new java.awt.BasicStroke(8));
        g2.setColor(Color.BLACK);
        g2.drawRect(1, 1, screenWidth-1, screenheight-1);
        g2.setStroke(new java.awt.BasicStroke(1));
        g2.setColor(Color.white);
        mouseg(g);
        g2.setColor(Color.red);
        g2.setFont(new Font("Consolas",Font.BOLD,25));
        g2.drawString("Points = "+contrat, 400, 25);
        g2.drawImage(Serp0, null, jugadorX*64, jugadorY*64);
        //g2.fillRect(jugadorX, jugadorY, tileSize, tileSize);
        g2.dispose();
    }
    private void GetBack(Graphics2D g2){
        try {
            String url = "x64x22.png";
            img = ImageIO.read(getClass().getResourceAsStream(url));
        } catch (IOException e) {
            System.out.println("Error ="+e);
        }
        GROUND1 = img.getSubimage(0, 0, 64, 64);
        GROUND2 = img.getSubimage(64, 0, 64, 64);
        Serp1 = img.getSubimage(192, 0, 64, 64);
        Serp2 = img.getSubimage(0, 64, 64, 64);
        Serp3 = img.getSubimage(64, 64, 64, 64);
        Serp4 = img.getSubimage(128, 64, 64, 64);
        mouse = img.getSubimage(128, 0, 64, 64);
    }
    private void mouseg(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if (aux3==false) {
            Generatorfood[jugadorX][jugadorY] = 2;
            Generatorfood[jugadorX-1][jugadorY] = 3;
            Serp0 = Serp1;
            aux3 = true;
        }
        MatrizNumSnake(Generatorfood);
        boolean aux4 = false;
        for (int i = 0; i < maxScreenCol; i++) {
            for (int j = 0; j < maxScreenRow; j++) {
                if (Generatorfood[i][j] == 1) {
                    if (aux4 ==false) {
                        ratonx = i;
                        ratony = j;
                        g.drawImage(mouse, ratonx*64, ratony*64, null);   
                        aux4 = true;
                    }
                }
                if (Generatorfood[i][j] == 2) {
                    
                }
                if (Generatorfood[i][j] == 3) {
                    Color colorbox= new Color(0,102,0);
                    g2.setColor(colorbox); 
                    g2.fillRect((jugadorX-1)*64,(jugadorY)*64, 64, 64);
                }
                if (jugadorX == ratonx && jugadorY == ratony) {
                    if (Generatorfood[i][j]==1) {
                        Generatorfood[i][j] = 0;
                        contrat++;
                    }
                }
            }
        }
    }
    boolean aux3 = false;
    private void MatrizNumSnake(int[][] generatorfood2) {
        boolean aux2 = false;
        for (int i = 0; i < Generatorfood.length; i++) {
            for (int j = 0; j < Generatorfood[i].length; j++) {
                if (Generatorfood[i][j] == 1) {
                    aux2=true;
                }
            }
        }
        if (aux2==false) {
            int random = (int)(Math.random()*(((maxScreenCol * maxScreenRow)-1+1)+1));
            boolean aux4 = false;
            for (int i = 0; i < Generatorfood.length; i++) {
                for (int j = 0; j < Generatorfood[i].length; j++) {
                    if ((i+j)== random) {
                        if(aux4 == false){
                            ratonx = i;
                            ratony = j;
                            Generatorfood[i][j] = 1;
                            aux4 = true;
                        }
                    }
                }
            }
        }
    }
}