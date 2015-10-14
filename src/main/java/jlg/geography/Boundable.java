package jlg.geography;


import jlg.geography.geometry.BoundingBox;
import jlg.geography.geometry.Point;

public interface Boundable {
    BoundingBox getBoundingBox();
    boolean contains(Point point);
}
