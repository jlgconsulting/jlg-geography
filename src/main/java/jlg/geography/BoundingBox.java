package jlg.geography;

/**
 * This class represents the bounding box of a geometry (line, polygon),
 * as the min(x), max(x), min(y), max(y) coordinates necessary to draw a
 * rectangle around the given geometry.
 */
public interface BoundingBox<TCoordinate> {
    TCoordinate getMinCoordinate();
    TCoordinate getMaxCoordinate();
    TCoordinate getCenterCoordinate();
    boolean contains(TCoordinate coordinate);
}
