package App;
import javax.swing.JFrame;
public class App {
    private static AppPanel appPanel;
    public static void main(String[] args){
    setVentana();
    appPanel.startApp();
}
    private static void setVentana() {
    JFrame ventana = new JFrame();
    ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventana.setResizable(false);
    ventana.setTitle("Juego2D");
    appPanel = new AppPanel();
    ventana.add(appPanel);
    ventana.pack();
    ventana.setLocationRelativeTo(null);
    ventana.setVisible(true);
    }
}