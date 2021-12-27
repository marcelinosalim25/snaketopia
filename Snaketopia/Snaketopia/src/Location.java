public class Location {
    private int x; //inisiasi variabel x
    private int y; // inisiasi variabel y

    public Location(int x, int y) {
        this.x = x; //mengeset bahwa lokasi x adalah x
        this.y = y; //mengeset bahwa lokasi y adalah y
    }

    public int getX() {
        return this.x; //return ke getx
    }

    public void setX(int x) {
        this.x = x; //
    }

    public int getY() {
        return this.y;  //return ke gety
    }

    public void setY(int y) {
        this.y = y; //
    }
}