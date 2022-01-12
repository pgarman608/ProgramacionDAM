package patata;
import javax.swing.JFrame;
public class Patata{
    private static Game game;
    public static void main(String[] args) {
        setv();
        game.SP();
    }
    private static void setv(){
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setResizable(false);
    ventana.setTitle("Juego2D");
    game = new Game();
    ventana.add(game);
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    }
}
