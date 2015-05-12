package jlg.geography.cartesian;

import jlg.geography.Boundable;
import jlg.geography.BoundingBox;

/**
 * Structure for holding cartesian information for a point in two dimensions
 */
public class Cartesian implements Boundable<Cartesian> {
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

    @Override
    public boolean isInBoundingBox(BoundingBox<Cartesian> boundingBox) {
        return boundingBox.contains(this);
    }
}
