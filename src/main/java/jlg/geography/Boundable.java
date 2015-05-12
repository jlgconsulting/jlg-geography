package jlg.geography;

public interface Boundable<TCoordinate> {
    boolean isInBoundingBox(BoundingBox<TCoordinate> boundingBox);
}
