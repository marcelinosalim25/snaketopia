import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public class SnakePanel implements ActionListener, KeyListener {
    private Renderer renderer;
    private int gamestat;
    private int dificulty;
    private Snake snake;
    private Point point;
    private Timer timer;
    private Random random;
    public static int score;
    private int move;
    int time;
    private boolean canmove;
    private ImageIcon title;

    public SnakePanel(Renderer renderer) {
        this.renderer = renderer;
        this.random = new Random();
        this.gamestat = 0;
        this.dificulty = 1;
        java.net.URL titleresource = getClass().getResource("title.png");
        this.title = new ImageIcon(titleresource); //logo game
    }

    public void gamestart() {
        this.snake = new Snake();
        this.point = new Point();
        this.score = 0;
        this.move = 1;
        this.gamestat = 1;
        this.canmove = true;
        this.time = 210 / this.dificulty;
        this.timer = new Timer(this.time, this);
        this.timer.start();
    }

    public void render(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (this.gamestat == 0) {
            //menampilkan laman menu
        	g.setFont(new Font("Times New Roman", Font.BOLD, 24));
        	g.setColor(Color.CYAN);
        	g.drawString("Snaketopia", 290, 100);
            g.setColor(Color.WHITE);
            this.title.paintIcon(this.renderer, g, 225, 150);
            g.setFont(new Font("Times New Roman", Font.BOLD, 20)); // atur tulisan pada menu
            g.drawString("SPACE to Start", 280, 270); //mengatur letak tulisan di menu
            switch(this.dificulty) {
               //tulisan dificulty yang ada di laman menu
                case 1:
                    g.drawString("<< Dificulty: Easy >>", 255, 300);
                    break;
                case 2:
                    g.drawString("<< Dificulty: Medium >>", 240, 300);
                    break;
                case 3:
                    g.drawString("<< Dificulty: Hard >>", 257, 300);
            }
        }
        else {
            g.setColor(Color.MAGENTA);
            g.drawRect(24, 24, 470, 420);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", Font.BOLD, 20));
            switch(this.dificulty) {
                case 1:
                    g.drawString("EASY", 555, 175);
                    break;
                case 2:
                    g.drawString("MEDIUM", 545, 175);
                    break;
                case 3:
                    g.drawString("HARD", 555, 175);
            }
            // score
            g.drawString("Score: " + this.score, 550, 200);
            		//panjang ular
            if (this.gamestat == 1) {
                //bila gamenya berjalan, maka terdapat tulisan pause di sisi kanan
                g.drawString("SPACE to Pause", 520, 225);
            } else if (this.gamestat == 3) {
                //resume atau keluar dari game
                g.drawString("SPACE to Continue", 500, 225);
                g.drawString("ESC to Exit", 535, 275);
            }

            this.snake.render(g, this.renderer);
            this.point.render(g);
            if (this.gamestat == 2) {
                //keterangan game over serta dapat mengulang dengan menekan spasi
            	g.setColor(Color.RED);
                g.setFont(new Font("Times New Roman", 1, 50));
                g.drawString("GAME OVER", 110, 225);
                g.setFont(new Font("Times New Roman", 1, 20));
                g.drawString("SPACE to restart", 200, 255);
                g.setFont(new Font("Times New Roman", 1, 20));
                g.drawString("ESC to Exit", 220 , 285);
            }
        }

    }

    public void actionPerformed(ActionEvent ae) {
        if (this.gamestat == 1) {
            // game over karena menabrak badan
            if (!this.snake.move(this.move)) {
                this.gamestat = 2;
                this.timer.stop();
            }

            if (this.snake.eat(this.point)) {
                //tambah point abis makan food
                if(this.score % 10 == 0 && this.score != 0) {
	            	this.point.move();
	                //this.snake.grow();
	                this.score += 2;
                }else {
                	this.point.move();
	                this.snake.grow();
	                this.score += 1;
                }
            }
        }

        this.renderer.repaint();
        this.canmove = true;
    }

    public void keyTyped(KeyEvent ke) {
    }

    public void keyPressed(KeyEvent ke) {
        if (this.gamestat == 0) {
            if (ke.getKeyCode() == 32) { //ASCII spasi
                this.gamestart();
                this.gamestat = 1;
            }
            if (ke.getKeyCode() == 39) { //ASCII right
                ++this.dificulty;
                if (this.dificulty > 3) {
                    this.dificulty = 1;
                }
            } else if (ke.getKeyCode() == 37) { //ASCII left
               
                --this.dificulty;
                if (this.dificulty < 1) {
                    this.dificulty = 3;
                }
            }

            this.renderer.repaint();
        } else if (this.gamestat == 1) {
            //fungsi pergerakan ular
            if (this.canmove) {
                switch(ke.getKeyCode()) {
                    case 32:
                        this.gamestat = 3;
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    default:
                        break;
                    case 37:
                        if (this.move != 1) {
                            this.move = 3;
                        }
                        break;
                    case 38:
                        if (this.move != 2) {
                            this.move = 0;
                        }
                        break;
                    case 39:
                        if (this.move != 3) {
                            this.move = 1;
                        }
                        break;
                    case 40:
                        if (this.move != 0) {
                            this.move = 2;
                        }
                }
                this.canmove = false;
            }
        } else if (this.gamestat == 2) {
            //gameover 
            if (ke.getKeyCode() == 32) {
                this.gamestart();
                this.gamestat = 1;
            } else if (ke.getKeyCode() == 27) {
                System.exit(0);
            }

            this.renderer.repaint();
        } else if(this.gamestat == 3 && ke.getKeyCode() == 32) {
            //kembali berjalan dari pause
            this.gamestat = 1;
        } else if(this.gamestat == 3 && ke.getKeyCode() == 27){
            //escape saat pause exit
            System.exit(0);
        }
    }
    public void keyReleased(KeyEvent ke) {
    }
}