package jlg.geography;

/**
 * Structure for holding cartesian information for a point in two dimensions
 */
public class Cartesian {
    private int x;
    private int y;

    public Cartesian(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
