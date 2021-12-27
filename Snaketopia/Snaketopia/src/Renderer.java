
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Renderer extends JPanel {
    SnakePanel gameplay = new SnakePanel(this);

    public Renderer() {
        this.addKeyListener(this.gameplay);//mengatur interface agar sesuai dengan keyboard
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 700, 500);
        this.gameplay.render((Graphics2D)g);
    }
}