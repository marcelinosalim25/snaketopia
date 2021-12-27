import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Graphics2D;

public class Point {
    private Location location;  
    private Random random = new Random(); 
    public Point() {
        this.location = new Location(this.random.nextInt(15) * 25 + 25, this.random.nextInt(15) * 25 + 25); //penempatan point ular secara random
    }

    public void render(Graphics g) { 
        if(SnakePanel.score%10 == 0 && SnakePanel.score != 0) {
	    	g.setColor(Color.GREEN);
	        g.fillOval(this.location.getX(), this.location.getY(), 20, 20);
        }else {
        	g.setColor(Color.RED);
	        g.fillOval(this.location.getX(), this.location.getY(), 20, 20);
        }
    }

    public Location getLocation() { // lokasi makanan ke location
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void move() {
        this.location = new Location(this.random.nextInt(15) * 25 + 25, this.random.nextInt(15) * 25 + 25); //random point setelah dimakan
    }
}