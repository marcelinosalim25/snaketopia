import javax.swing.JFrame;

public class SnakeFrame {

    private static Renderer renderer; //memanggil class renderer

    public SnakeFrame() {
    
        JFrame frame = new JFrame("Snake"); 
        frame.setBounds(0, 0, 700, 500); //mengatur batas-batas
        frame.setResizable(false); //agar tidak dapat di resize
        frame.setDefaultCloseOperation(3); //menutup window secara default
        renderer = new Renderer(); //mendeklarasikan class renderer ke dalam kelas main
        frame.add(renderer); //memasukan class renderer ke dalam JFrame
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    
    }
}